package com.cheetah.message.mq.provider.reveiver.eventbus;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.handler.api.consume.ConsumeService;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import com.cheetah.message.mq.provider.mq.eventbus.EventBusListener;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/18 20:00
 */
@Component
@ConditionalOnProperty(name = "cheetah-mq-pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusReceiver implements EventBusListener {

    @Reference
    private ConsumeService consumeService;


    @Override
    public void consume(List<TaskInfo> lists) {
        consumeService.consume2Send(lists);
    }

    @Override
    public void recall(MessageTemplate messageTemplate) {
        consumeService.consume2recall(messageTemplate);
    }
}
