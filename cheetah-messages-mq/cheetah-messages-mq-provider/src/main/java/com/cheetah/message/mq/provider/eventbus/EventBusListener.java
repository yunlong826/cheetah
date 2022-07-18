package com.cheetah.message.mq.provider.eventbus;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 监听器
 * @date 2022/7/18 19:59
 */
public interface EventBusListener {
    /**
     * 消费消息
     * @param lists
     */
    void consume(List<TaskInfo> lists);

    /**
     * 撤回消息
     * @param messageTemplate
     */
    void recall(MessageTemplate messageTemplate);
}
