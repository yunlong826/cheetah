package com.cheetah.message.handler.provider.handler.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.cheetah.message.common.constant.AustinConstant;
import com.cheetah.message.common.constant.SendAccountConstant;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.common.dto.model.DingDingRobotContentModel;
import com.cheetah.message.common.enums.ChannelType;
import com.cheetah.message.common.enums.SendMessageType;
import com.cheetah.message.handler.api.handler.Handler;
import com.cheetah.message.handler.provider.account.DingDingRobotAccount;
import com.cheetah.message.handler.provider.domain.dingding.DingDingRobotParam;
import com.cheetah.message.handler.provider.domain.dingding.DingDingRobotResult;
import com.cheetah.message.handler.provider.handler.BaseHandler;
import com.cheetah.message.handler.provider.utils.AccountUtils;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Description: 钉钉消息自定义机器人 消息处理器
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 19:07
 */
@Slf4j
@Service
public class DingDingRobotHandler extends BaseHandler implements Handler {

    @Autowired
    private AccountUtils accountUtils;

    public DingDingRobotHandler(){
        channelCode = ChannelType.DING_DING_ROBOT.getCode();
    }
    @Override
    public void recall(MessageTemplate messageTemplate) {

    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        try{
            DingDingRobotAccount account = accountUtils.getAccount(taskInfo.getSendAccount(),
                    SendAccountConstant.DING_DING_ROBOT_ACCOUNT_KEY,
                    SendAccountConstant.DING_DING_ROBOT_PREFIX,
                    DingDingRobotAccount.class);
            DingDingRobotParam dingDingRobotParam = assembleParam(taskInfo);

            String httpResult = HttpUtil.post(assembleParamUrl(account), JSON.toJSONString(dingDingRobotParam));
            DingDingRobotResult dingDingRobotResult = JSON.parseObject(httpResult, DingDingRobotResult.class);
            if (dingDingRobotResult.getErrCode() == 0) {
                return true;
            }
            // 常见的错误 应当 关联至 AnchorState,由austin后台统一透出失败原因
            log.error("DingDingHandler#handler fail!result:{},params:{}", JSON.toJSONString(dingDingRobotResult), JSON.toJSONString(taskInfo));
        }catch (Exception e){
            log.error("DingDingHandler#handler fail!e:{},params:{}", Throwables.getStackTraceAsString(e), JSON.toJSONString(taskInfo));
        }
        return false;
    }

    private DingDingRobotParam assembleParam(TaskInfo taskInfo) {

        // 接收者相关
        DingDingRobotParam.AtVO atVo = DingDingRobotParam.AtVO.builder().build();
        if (AustinConstant.SEND_ALL.equals(CollUtil.getFirst(taskInfo.getReceiver()))) {
            atVo.setIsAtAll(true);
        } else {
            atVo.setAtUserIds(new ArrayList<>(taskInfo.getReceiver()));
        }

        // 消息类型以及内容相关
        DingDingRobotContentModel contentModel = (DingDingRobotContentModel) taskInfo.getContentModel();
        DingDingRobotParam param = DingDingRobotParam.builder().at(atVo)
                .msgtype(SendMessageType.getDingDingRobotTypeByCode(contentModel.getSendType()))
                .build();
        if (SendMessageType.TEXT.getCode().equals(contentModel.getSendType())) {
            param.setText(DingDingRobotParam.TextVO.builder().content(contentModel.getContent()).build());
        }
        if (SendMessageType.MARKDOWN.getCode().equals(contentModel.getSendType())) {
            param.setMarkdown(DingDingRobotParam.MarkdownVO.builder().title(contentModel.getTitle()).text(contentModel.getContent()).build());
        }
        if (SendMessageType.LINK.getCode().equals(contentModel.getSendType())) {
            param.setLink(DingDingRobotParam.LinkVO.builder().title(contentModel.getTitle()).text(contentModel.getContent()).messageUrl(contentModel.getUrl()).picUrl(contentModel.getPicUrl()).build());
        }
        if (SendMessageType.NEWS.getCode().equals(contentModel.getSendType())) {
            List<DingDingRobotParam.FeedCardVO.LinksVO> linksVOS = JSON.parseArray(contentModel.getFeedCards(), DingDingRobotParam.FeedCardVO.LinksVO.class);
            DingDingRobotParam.FeedCardVO feedCardVO = DingDingRobotParam.FeedCardVO.builder().links(linksVOS).build();
            param.setFeedCard(feedCardVO);
        }
        if (SendMessageType.ACTION_CARD.getCode().equals(contentModel.getSendType())) {
            List<DingDingRobotParam.ActionCardVO.BtnsVO> btnsVOS = JSON.parseArray(contentModel.getBtns(), DingDingRobotParam.ActionCardVO.BtnsVO.class);
            DingDingRobotParam.ActionCardVO actionCardVO = DingDingRobotParam.ActionCardVO.builder().title(contentModel.getTitle()).text(contentModel.getContent()).btnOrientation(contentModel.getBtnOrientation()).btns(btnsVOS).build();
            param.setActionCard(actionCardVO);
        }

        return param;
    }
    /**
     * 拼装 url
     *
     * @param account
     * @return
     */
    private String assembleParamUrl(DingDingRobotAccount account) {
        long currentTimeMillis = System.currentTimeMillis();
        String sign = assembleSign(currentTimeMillis, account.getSecret());
        return (account.getWebhook() + "&timestamp=" + currentTimeMillis + "&sign=" + sign);
    }

    /**
     * 使用HmacSHA256算法计算签名
     *
     * @param currentTimeMillis
     * @param secret
     * @return
     */
    private String assembleSign(long currentTimeMillis, String secret) {
        String sign = "";
        try {
            String stringToSign = currentTimeMillis + String.valueOf(StrUtil.C_LF) + secret;
            Mac mac = Mac.getInstance(AustinConstant.HMAC_SHA256_ENCRYPTION_ALGO);
            mac.init(new SecretKeySpec(secret.getBytes(AustinConstant.CHARSET_NAME), AustinConstant.HMAC_SHA256_ENCRYPTION_ALGO));
            byte[] signData = mac.doFinal(stringToSign.getBytes(AustinConstant.CHARSET_NAME));
            sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)), AustinConstant.CHARSET_NAME);
        } catch (Exception e) {
            log.error("DingDingHandler#assembleSign fail!:{}", Throwables.getStackTraceAsString(e));
        }
        return sign;
    }
}
