package com.cheetah.message.cron.service;

/**
 * 具体处理定时任务逻辑的Handler
 */
public interface TaskHandler {
    /**
     * 处理具体的逻辑
     *
     * @param messageTemplateId
     */
    void handle(Long messageTemplateId);
}
