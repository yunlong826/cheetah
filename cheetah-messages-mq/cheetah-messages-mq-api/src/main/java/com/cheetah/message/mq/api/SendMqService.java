package com.cheetah.message.mq.api;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送数据至消息队列
 * @date 2022/7/18 19:48
 */
public interface SendMqService {
    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     * @param tagId
     */
    void send(String topic, String jsonValue, String tagId);


    /**
     * 发送消息
     *
     * @param topic
     * @param jsonValue
     */
    void send(String topic, String jsonValue);
}
