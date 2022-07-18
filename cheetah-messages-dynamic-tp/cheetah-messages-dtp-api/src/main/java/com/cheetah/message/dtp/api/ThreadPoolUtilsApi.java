package com.cheetah.message.dtp.api;

import com.dtp.core.thread.DtpExecutor;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/18 23:32
 */
public interface ThreadPoolUtilsApi {
    void register(DtpExecutor dtpExecutor);
}
