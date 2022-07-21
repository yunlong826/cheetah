package com.cheetah.message.mq.provider.reveiver.rabbitmq;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.handler.api.consume.ConsumeService;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description: 消费mq消息
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/21 19:28
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@ConditionalOnProperty(name = "cheetah-mq-pipeline",havingValue = MessageQueuePipeline.RABBIT_MQ)
public class Receiver {
    @Reference
    private ConsumeService consumeService;




    /**
     *  消费消息
     * @param message
     */
    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "${cheetah.business.group.value}")
            ,exchange = @Exchange(value = "#{'${cheetah.business.exchange.name}'}"
            ,type = ExchangeTypes.DIRECT),key = {"${cheetah.business.group.value}"})})
    public void consumer(String message){
        if(StrUtil.isNotBlank(message)){
            List<TaskInfo> taskInfos = JSON.parseArray(message, TaskInfo.class);

            consumeService.consume2Send(taskInfos);
        }
    }


    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value = "${cheetah.business.recall.group.name}")
            ,exchange = @Exchange(value = "#{'${cheetah.business.recall.exchange.name}'}"
            ,type = ExchangeTypes.DIRECT),key = {"${cheetah.business.recall.group.name}"})})
    public void recall(String message){
        if(StrUtil.isNotBlank(message)){
            MessageTemplate messageTemplate = JSON.parseObject(message, MessageTemplate.class);
            consumeService.consume2recall(messageTemplate);
        }
    }


}
