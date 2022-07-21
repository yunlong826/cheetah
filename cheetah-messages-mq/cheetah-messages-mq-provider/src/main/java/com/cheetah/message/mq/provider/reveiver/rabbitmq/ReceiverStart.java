package com.cheetah.message.mq.provider.reveiver.rabbitmq;

import com.cheetah.message.handler.api.group.GroupIdMappingApi;
import com.cheetah.message.mq.provider.constants.MessageQueuePipeline;
import com.rabbitmq.client.AMQP;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Properties;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/21 23:42
 */
@Component
@ConditionalOnProperty(name = "cheetah-mq-pipeline",havingValue = MessageQueuePipeline.RABBIT_MQ)
public class ReceiverStart implements InitializingBean {

    @Reference
    private GroupIdMappingApi groupIdMappingApi;

    @Autowired
    private SimpleMessageListenerContainer listenerContainer;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Value("${cheetah.business.topic.name}")
    private String topic;

    @Value("${cheetah.business.recall.topic.name}")
    private String recallTopic;

    @Autowired
    private ReceiverListener receiverListener;

    @Autowired
    private ReceiverRecallListener receiverRecallListener;



    private void createQueue(String queueName,String exchangeName,String routeKey){
        Properties queueProperties = rabbitAdmin.getQueueProperties(queueName);
        if(queueProperties == null){
            Queue queue = new Queue(queueName,true,false,false,null);
            DirectExchange directExchange = new DirectExchange(exchangeName);

            rabbitAdmin.declareQueue(queue);
            rabbitAdmin.declareExchange(directExchange);
            rabbitAdmin.declareBinding(BindingBuilder.bind(queue).to(directExchange).with(routeKey));
            listenerContainer.addQueues(queue);
            if(exchangeName.equals(this.topic)){
                listenerContainer.setMessageListener(receiverListener);
            }else{
                listenerContainer.setMessageListener(receiverRecallListener);
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<String> allGroupIds = groupIdMappingApi.getAllGroupIds();
        for(int i = 0;i<allGroupIds.size();i++){
            // 创建消息队列
            createQueue(allGroupIds.get(i),topic,allGroupIds.get(i));

            // 创建撤回消息队列
            createQueue(allGroupIds.get(i),recallTopic,allGroupIds.get(i));
        }

    }
}
