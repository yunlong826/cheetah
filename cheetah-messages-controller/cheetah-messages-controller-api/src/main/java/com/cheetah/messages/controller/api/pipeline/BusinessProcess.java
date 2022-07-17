package com.cheetah.messages.controller.api.pipeline;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 业务执行器
 * @date 2022/7/17 18:29
 */
public interface BusinessProcess<T extends ProcessModel> {

    /**
     * 真正处理逻辑
     * @param context
     */
    void process(ProcessContext<T> context);
}
