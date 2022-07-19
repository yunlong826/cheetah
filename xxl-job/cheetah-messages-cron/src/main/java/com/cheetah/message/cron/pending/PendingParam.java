package com.cheetah.message.cron.pending;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

/**
 * @author jack_yun
 * @version 1.0
 * @description: pending 初始化参数类
 * @date 2022/7/19 22:55
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class PendingParam<T> {
    /**
     * 阻塞队列实现类【必填】
     */
    private BlockingQueue<T> queue;

    /**
     * batch 触发执行的数量阈值【必填】
     */
    private Integer numThreshold;

    /**
     * batch 触发执行的时间阈值，单位毫秒【必填】
     */
    private Long timeThreshold;

    /**
     * 消费线程池实例【必填】
     */
    protected ExecutorService executorService;
}
