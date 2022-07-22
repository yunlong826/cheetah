package com.cheetah.message.mq.provider.reveiver.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.handler.api.consume.ConsumeService;
import com.rabbitmq.client.Channel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/22 0:14
 */
@Component
public class ReceiverRecallListener implements ChannelAwareMessageListener {

    @Reference
    private ConsumeService consumeService;

    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        recall(new String(message.getBody()));
    }

    public void recall(String message){
        if(StrUtil.isNotBlank(message)){
            MessageTemplate messageTemplate = JSON.parseObject(message, MessageTemplate.class);
            consumeService.consume2recall(messageTemplate);
        }
    }
}
