package com.cheetah.message.mq.provider.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.cheetah.message.mq.provider.reveiver.rabbitmq.ReceiverListener;
import com.cheetah.message.mq.provider.reveiver.rabbitmq.ReceiverRecallListener;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/21 23:32
 */
@Configuration
public class RabbitMQConfig {

    @Autowired
    private ReceiverListener receiverListener;

    @Autowired
    private ReceiverRecallListener receiverRecallListener;

    @NacosValue(value = "${cheetah.rabbitmq.address}",autoRefreshed = true)
    private String address;

    @NacosValue(value = "${cheetah.rabbitmq.username}",autoRefreshed = true)
    private String username;

    @NacosValue(value = "${cheetah.rabbitmq.password}",autoRefreshed = true)
    private String password;

    @Bean(name = "simpleMessageListenerContainer")
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerContainer
                = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setMessageListener(receiverListener);
        return simpleMessageListenerContainer;
    }

    @Bean(name = "simpleMessageListenerRecallContainer")
    public SimpleMessageListenerContainer simpleMessageListenerRecallContainer(ConnectionFactory connectionFactory){
        SimpleMessageListenerContainer simpleMessageListenerRecallContainer
                = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerRecallContainer.setMessageListener(receiverRecallListener);
        return simpleMessageListenerRecallContainer;
    }


    /**
     *  启动发送消息队列监听器与撤回消息队列
     * @param connectionFactory
     */
    @Bean
    public void start(ConnectionFactory connectionFactory){
        simpleMessageListenerContainer(connectionFactory).start();
        simpleMessageListenerRecallContainer(connectionFactory).start();
    }

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);

        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory){
        return new RabbitTemplate(connectionFactory);
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory){
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);

        //autoStartup 必须要设为 true ，否则Spring容器不会加载RabbitAdmin类
        rabbitAdmin.setAutoStartup(true);
        return rabbitAdmin;
    }
}
