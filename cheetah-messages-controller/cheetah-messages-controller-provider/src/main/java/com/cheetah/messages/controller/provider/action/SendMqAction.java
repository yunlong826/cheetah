package com.cheetah.messages.controller.provider.action;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.enums.BusinessCode;
import com.cheetah.message.common.enums.RespStatusEnum;
import com.cheetah.message.common.vo.BasicResultVO;
import com.cheetah.message.handler.api.group.GroupIdMappingApi;
import com.cheetah.message.mq.api.SendMqService;
import com.cheetah.messages.controller.api.domain.SendTaskModel;
import com.cheetah.messages.controller.api.pipeline.BusinessProcess;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 19:52
 */
@Service
@Slf4j
public class SendMqAction implements BusinessProcess<SendTaskModel> {

    @Reference
    private SendMqService sendMqService;

    @Reference
    private GroupIdMappingApi groupIdMappingApi;

    @NacosValue(value = "${cheetah.business.topic.name}",autoRefreshed = true)
    private String topic;

    @NacosValue(value = "${cheetah.business.recall.topic.name}",autoRefreshed = true)
    private String recallTopic;


    @Override
    public void process(ProcessContext<SendTaskModel> context) {
        SendTaskModel sendTaskModel = context.getProcessModel();
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();
        String groupIdByTaskInfo =
                groupIdMappingApi.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfo.iterator()));

        try {
            if (BusinessCode.COMMON_SEND.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getTaskInfo(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(topic, message
                        ,groupIdByTaskInfo);
            } else if (BusinessCode.RECALL.getCode().equals(context.getCode())) {
                String message = JSON.toJSONString(sendTaskModel.getMessageTemplate(), new SerializerFeature[]{SerializerFeature.WriteClassName});
                sendMqService.send(recallTopic, message
                        ,groupIdByTaskInfo);
            }
        } catch (Exception e) {
            context.setNeedBreak(true).setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send rabbitMq fail! e:{},params:{}", Throwables.getStackTraceAsString(e)
                    , JSON.toJSONString(CollUtil.getFirst(sendTaskModel.getTaskInfo().listIterator())));
        }
    }
}
