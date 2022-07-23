package com.cheetah.message.handler.provider.service;

import com.cheetah.message.dtp.api.HandlerThreadPoolApi;
import com.cheetah.message.dtp.api.ThreadPoolUtilsApi;
import com.cheetah.message.handler.api.TaskPendingHolderApi;
import com.cheetah.message.handler.provider.utils.GroupIdMappingUtils;
import com.dtp.core.thread.DtpExecutor;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * @author jack_yun
 * @version 1.0
 * @description:  存储 每种消息类型 与 TaskPending 的关系
 * @date 2022/7/18 20:28
 */
@Service
public class TaskPendingHolderProvider implements TaskPendingHolderApi {

    @Reference
    private ThreadPoolUtilsApi threadPoolUtils;

    @Reference
    private HandlerThreadPoolApi handlerThreadPoolApi;

    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    /**
     * 获取得到所有的groupId
     */
    private static List<String> groupIds = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 给每个渠道，每种消息类型初始化一个线程池
     */
    @PostConstruct
    public void init(){

        /**
         *
         * 可以通过 nacos 配置：dynamic-tp-nacos-dtp.yml  动态修改线程池的信息
         */
        for(String groupId:groupIds){
            DtpExecutor executor = handlerThreadPoolApi.getExecutor(groupId);

            threadPoolUtils.register(executor);

            taskPendingHolder.put(groupId,executor);
        }
    }

    /**
     * 得到对应得线程池
     *
     * @param groupId
     * @return
     */
    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}
