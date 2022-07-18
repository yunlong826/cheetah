package com.cheetah.message.dtp.provider.service;

import com.cheetah.message.dtp.api.ThreadPoolUtilsApi;
import com.cheetah.message.dtp.provider.shutdown.ThreadPoolExecutorShutdownDefinition;
import com.dtp.core.DtpRegistry;
import com.dtp.core.thread.DtpExecutor;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/18 23:43
 */
@Service
public class ThreadPoolUtilsService implements ThreadPoolUtilsApi {

    @Autowired
    private ThreadPoolExecutorShutdownDefinition shutdownDefinition;

    private static final String SOURCE_NAME = "cheetah";

    /**
     * 1. 将当前线程池 加入到 动态线程池内
     * 2. 注册 线程池 被Spring管理，优雅关闭
     */
    @Override
    public void register(DtpExecutor dtpExecutor) {
        DtpRegistry.register(dtpExecutor, SOURCE_NAME);
        shutdownDefinition.registryExecutor(dtpExecutor);
    }
}
