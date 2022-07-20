package com.cheetah.message.mq.provider.mq.rabbitmq;

import com.cheetah.message.mq.api.SendMqService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送消息到rabbitMq
 * @date 2022/7/20 23:05
 */
@Service
public class RabbitMqSendMqServiceImpl implements SendMqService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(String topic, String jsonValue, String tagId) {

    }

    @Override
    public void send(String topic, String jsonValue) {

    }
}
