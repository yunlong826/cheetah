package com.cheetah.message.send.api.service;

import com.cheetah.message.common.domain.BatchSendRequest;
import com.cheetah.message.common.domain.SendRequest;
import com.cheetah.message.common.domain.SendResponse;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送消息的接口
 * @date 2022/7/17 17:07
 */
public interface SendService {
    /**
     * 单条消息发送接口
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);


    /**
     * 多条消息发送接口
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);
}
