package com.cheetah.message.handler.provider.service;

import com.cheetah.message.handler.api.TaskPendingHolderApi;
import org.apache.dubbo.config.annotation.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/18 20:28
 */
@Service
public class TaskPendingHolderProvider implements TaskPendingHolderApi {

    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);
}
