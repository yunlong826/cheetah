package com.cheetah.message.mq.provider.mq.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.cheetah.message.mq.api.SendMqService;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送消息到rabbitMq
 * @date 2022/7/20 23:05
 */
@Service
@ConditionalOnProperty(name = "cheetah.mq.pipeline",havingValue = MessageQueuePipeline.RABBIT_MQ)
public class RabbitMqSendMqServiceImpl implements SendMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

//    @Value("${cheetah.business.recall.group.name}")
//    private String recallId;

    @Override
    public void send(String exchange, String jsonValue, String routeKey) {
        rabbitTemplate.convertAndSend(exchange,routeKey,jsonValue);
    }

    @Override
    public void send(String exchange, String jsonValue) {
//        send(exchange,jsonValue,recallId);
    }
}
