package com.cheetah.message.mq.provider.eventbus;

import com.alibaba.fastjson.JSON;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.mq.api.SendMqService;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/18 19:49
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "cheetah-mq-pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusSendMqServiceImpl implements SendMqService {

    private EventBus eventBus = new EventBus();

    @Autowired
    private EventBusListener eventBusListener;

    @Value("${cheetah.business.topic.name}")
    private String sendTopic;
    @Value("${cheetah.business.recall.topic.name}")
    private String recallTopic;
    @Override
    public void send(String topic, String jsonValue, String tagId) {
        eventBus.register(eventBusListener);
        if (topic.equals(sendTopic)) {
            eventBus.post(JSON.parseArray(jsonValue, TaskInfo.class));
        } else if (topic.equals(recallTopic)) {
            eventBus.post(JSON.parseObject(jsonValue, MessageTemplate.class));
        }
    }

    @Override
    public void send(String topic, String jsonValue) {
        send(topic, jsonValue, null);
    }
}
