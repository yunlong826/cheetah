package com.cheetah.message.handler.api.handler;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 消息处理器
 * @date 2022/7/18 20:20
 */
public interface Handler {
    /**
     * 处理器
     *
     * @param taskInfo
     */
    void doHandler(TaskInfo taskInfo);

    /**
     * 撤回消息
     *
     * @param messageTemplate
     * @return
     */
    void recall(MessageTemplate messageTemplate);
}
