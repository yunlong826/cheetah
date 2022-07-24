package com.cheetah.message.handler.provider.discard;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.cheetah.message.common.constant.AustinConstant;
import com.cheetah.message.common.domain.AnchorInfo;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.enums.AnchorState;
import com.cheetah.message.handler.provider.service.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 丢弃模板消息
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/22 17:16
 */
@Service
@Slf4j
public class DiscardMessageService {

    private static final String DISCARD_MESSAGE_KEY = "discardMsgIds";

    @Autowired
    private ConfigService configService;

    /**
     *  丢弃消息，配置信息在apollo
     * @param taskInfo
     * @return
     */
    public boolean isDiscard(TaskInfo taskInfo){
        JSONArray array = JSON.parseArray(configService.getProperty(DISCARD_MESSAGE_KEY
        , AustinConstant.NACOS_DEFAULT_VALUE_JSON_ARRAY));

        if(array.contains(String.valueOf(taskInfo.getMessageTemplateId()))){
            log.info("消息为{}将被丢弃"
                , AnchorInfo.builder()
                            .businessId(taskInfo.getBusinessId())
                            .ids(taskInfo.getReceiver())
                            .state(AnchorState.DISCARD.getCode())
                            .build());
            return true;
        }
        return false;
    }
}
