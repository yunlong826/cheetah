package com.cheetah.message.cron.constant;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 延迟缓冲 pending 常量信息
 * @date 2022/7/19 23:07
 */
public class PendingConstant {
    /**
     * 阻塞队列大小
     */
    public static final Integer QUEUE_SIZE = 100;

    /**
     * 触发执行的数量阈值
     */
    public static final Integer NUM_THRESHOLD = 100;

    /**
     * batch 触发执行的时间阈值，单位毫秒【必填】
     */
    public static final Long TIME_THRESHOLD = 1000L;
}
