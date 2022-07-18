package com.cheetah.message.provider.service;

import com.cheetah.message.common.domain.BatchSendRequest;
import com.cheetah.message.common.domain.SendRequest;
import com.cheetah.message.common.domain.SendResponse;
import com.cheetah.message.common.vo.BasicResultVO;
import com.cheetah.message.send.api.service.SendService;
import com.cheetah.messages.controller.api.ProcessController;
import com.cheetah.messages.controller.api.domain.SendTaskModel;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import java.util.Collections;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送接口 注册到zk中心，进行微服务调用
 * @date 2022/7/17 17:31
 */
@Service
public class SendServiceImpl implements SendService {

    @Reference
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(sendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success())
                .build();

        ProcessContext process = processController.process(context);
        return new SendResponse(process.getResponse().getStatus(),process.getResponse().getMsg());
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        ProcessContext context = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .needBreak(false)
                .response(BasicResultVO.success()).build();

        ProcessContext process = processController.process(context);

        return new SendResponse(process.getResponse().getStatus(), process.getResponse().getMsg());
    }
}
