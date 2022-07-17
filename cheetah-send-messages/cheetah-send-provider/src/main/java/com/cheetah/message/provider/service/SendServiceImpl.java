package com.cheetah.message.provider.service;

import com.cheetah.message.common.domain.BatchSendRequest;
import com.cheetah.message.common.domain.SendRequest;
import com.cheetah.message.common.domain.SendResponse;
import com.cheetah.message.send.api.service.SendService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送接口 注册到zk中心，进行微服务调用
 * @date 2022/7/17 17:31
 */
@Service
public class SendServiceImpl implements SendService {
    @Override
    public SendResponse send(SendRequest sendRequest) {
        return null;
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        return null;
    }
}
