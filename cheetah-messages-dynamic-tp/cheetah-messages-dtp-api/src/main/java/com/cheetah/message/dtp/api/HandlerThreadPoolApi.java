package com.cheetah.message.dtp.api;

import com.dtp.core.thread.DtpExecutor;

/**
 * 消息处理器线程池api接口
 */
public interface HandlerThreadPoolApi {

     DtpExecutor getExecutor(String groupId);
}
