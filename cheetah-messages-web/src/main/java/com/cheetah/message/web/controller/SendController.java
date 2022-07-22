package com.cheetah.message.web.controller;

import com.cheetah.message.common.domain.SendRequest;
import com.cheetah.message.common.domain.SendResponse;
import com.cheetah.message.web.service.SendMessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 发送接口
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/22 19:46
 */
@Api(tags = {"发送接口"})
@RestController
public class SendController {

    @Autowired
    private SendMessageService sendMessageService;

    @ApiOperation(value = "下发接口")
    @PostMapping("/send")
    public SendResponse send(@RequestBody SendRequest sendRequest){
        return sendMessageService.send(sendRequest);
    }
}
