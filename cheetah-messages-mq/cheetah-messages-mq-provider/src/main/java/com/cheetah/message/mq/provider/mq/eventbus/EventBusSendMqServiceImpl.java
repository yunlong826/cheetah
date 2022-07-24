package com.cheetah.message.mq.provider.mq.eventbus;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.config.annotation.NacosValue;
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
 * @description:
 * @date 2022/7/18 19:49
 */
@Slf4j
@Service
@ConditionalOnProperty(name = "cheetah-mq-pipeline", havingValue = MessageQueuePipeline.EVENT_BUS)
public class EventBusSendMqServiceImpl implements SendMqService {

    private EventBus eventBus = new EventBus();

    @Autowired
    private EventBusListener eventBusListener;

    @NacosValue(value = "${cheetah.business.topic.name}",autoRefreshed = true)
    private String sendTopic;
    @NacosValue(value = "${cheetah.business.recall.topic.name}",autoRefreshed = true)
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
