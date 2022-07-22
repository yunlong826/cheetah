package com.cheetah.message.mq.provider.reveiver.rabbitmq;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.handler.api.consume.ConsumeService;
import com.rabbitmq.client.Channel;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/22 0:11
 */
@Component
public class ReceiverListener implements ChannelAwareMessageListener {

    @Reference
    private ConsumeService consumeService;
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {
        if(StrUtil.isNotBlank(new String(message.getBody()))){
            List<TaskInfo> taskInfos = JSON.parseArray(new String(message.getBody()), TaskInfo.class);

            consumeService.consume2Send(taskInfos);
        }
    }
}
