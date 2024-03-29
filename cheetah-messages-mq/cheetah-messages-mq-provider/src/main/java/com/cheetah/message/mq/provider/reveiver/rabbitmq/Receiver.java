package com.cheetah.message.mq.provider.reveiver.rabbitmq;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cheetah.message.handler.api.group.GroupIdMappingApi;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import com.rabbitmq.client.AMQP;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/21 23:42
 */
@Component
@ConditionalOnProperty(name = "cheetah.mq.pipeline",havingValue = MessageQueuePipeline.RABBIT_MQ)
@Slf4j
public class Receiver implements InitializingBean {

    @Reference
    private GroupIdMappingApi groupIdMappingApi;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @NacosValue(value = "${cheetah.business.topic.name}",autoRefreshed = true)
    private String topic;

    @NacosValue(value = "${cheetah.business.recall.topic.name}",autoRefreshed = true)
    private String recallTopic;


    @Resource(name = "simpleMessageListenerRecallContainer")
    private SimpleMessageListenerContainer listenerRecallContainer;

    @Resource(name = "simpleMessageListenerContainer")
    private SimpleMessageListenerContainer listenerContainer;

    private void createQueue(String queueName
            , DirectExchange directExchange
            ,String routeKey){
        Properties queueProperties = rabbitAdmin.getQueueProperties(queueName);
        Queue queue = new Queue(queueName,true,false,false,null);
        if(queueProperties == null){
            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareExchange(directExchange);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routeKey));
        }
        if(directExchange.getName().equals(this.topic)){
            listenerContainer.addQueues(queue);
        }else{
            listenerRecallContainer.addQueues(queue);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> allGroupIds = groupIdMappingApi.getAllGroupIds();
        DirectExchange exchange = new DirectExchange(topic);
        DirectExchange recallExchange = new DirectExchange(recallTopic);
        for(int i = 0;i<allGroupIds.size();i++){
            // 创建消息队列
            createQueue(allGroupIds.get(i),exchange,allGroupIds.get(i));

            // 创建撤回消息队列
            createQueue("recall"+allGroupIds.get(i),recallExchange,allGroupIds.get(i));
        }
        log.info("listenerContainer监听的队列数量为:{}",listenerContainer.getQueueNames().length);
        log.info("listenerRecallContainer监听的队列数量为:{}",listenerRecallContainer.getQueueNames().length);

    }
}
