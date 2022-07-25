package com.cheetah.message.handler.provider.pool;

import com.cheetah.message.common.constant.ThreadPoolConstant;
import com.dtp.common.em.QueueTypeEnum;
import com.dtp.common.em.RejectedTypeEnum;
import com.dtp.core.thread.DtpExecutor;
import com.dtp.core.thread.ThreadPoolBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Description: 消息处理器线程池接口
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/25 20:52
 */
@Service
public class HandlerThreadPool {
    private static final String PRE_FIX = "cheetah.";


    /**
     * 业务：处理某个渠道的某种类型消息的线程池
     * 配置：不丢弃消息，核心线程数不会随着keepAliveTime而减少(不会被回收)
     * 动态线程池且被Spring管理：true
     *
     * @return
     */
    public DtpExecutor getExecutor(String groupId) {
        return  ThreadPoolBuilder.newBuilder()
                .threadPoolName(PRE_FIX + groupId)
                .corePoolSize(ThreadPoolConstant.COMMON_CORE_POOL_SIZE)
                .maximumPoolSize(ThreadPoolConstant.COMMON_MAX_POOL_SIZE)
                .keepAliveTime(ThreadPoolConstant.COMMON_KEEP_LIVE_TIME)
                .timeUnit(TimeUnit.SECONDS)
                .rejectedExecutionHandler(RejectedTypeEnum.CALLER_RUNS_POLICY.getName())
                .allowCoreThreadTimeOut(false)
                .workQueue(QueueTypeEnum.VARIABLE_LINKED_BLOCKING_QUEUE.getName(), ThreadPoolConstant.COMMON_QUEUE_SIZE, false)
                .buildDynamic();

    }
}
