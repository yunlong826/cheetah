package com.cheetah.message.mq.provider.constants;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/18 19:54
 */
public interface MessageQueuePipeline {
    String EVENT_BUS = "eventBus";
    String KAFKA = "kafka";
    String RABBIT_MQ = "rabbitMq";
}
