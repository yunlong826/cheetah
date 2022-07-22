package com.cheetah.message.web.service;


import com.cheetah.message.common.domain.SendRequest;
import com.cheetah.message.common.domain.SendResponse;
import com.cheetah.message.send.api.service.SendService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/22 19:48
 */
@Service
public class SendMessageService {
    @Reference
    private SendService sendService;

    /**
     * 发送消息
     * @param sendRequest
     * @return
     */
    public SendResponse send(SendRequest sendRequest){
        return sendService.send(sendRequest);
    }
}
