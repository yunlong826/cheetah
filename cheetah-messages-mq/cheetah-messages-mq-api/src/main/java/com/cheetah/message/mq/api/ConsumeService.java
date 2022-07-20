package com.cheetah.message.mq.api;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 消费消息服务
 * @date 2022/7/20 21:30
 */
public interface ConsumeService {
    /**
     * 从MQ拉到消息进行消费，发送消息
     *
     * @param taskInfoLists
     */
    void consume2Send(List<TaskInfo> taskInfoLists);


    /**
     * 从MQ拉到消息进行消费，撤回消息
     *
     * @param messageTemplate
     */
    void consume2recall(MessageTemplate messageTemplate);
}
