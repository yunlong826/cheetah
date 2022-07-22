package com.cheetah.message.handler.api;

import java.util.concurrent.ExecutorService;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/18 20:27
 */
public interface TaskPendingHolderApi {
    ExecutorService route(String groupId);

}
