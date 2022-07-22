package com.cheetah.message.handler.provider.pending;


import cn.hutool.core.collection.CollUtil;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.handler.provider.discard.DiscardMessageService;
import com.cheetah.message.handler.provider.service.HandlerHolderProvider;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description:
 * Task 执行器
 * 0.丢弃消息
 * 2.屏蔽消息
 * 2.通用去重功能
 * 3.发送消息
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 21:48
 * TODO: task任务只完成部分，还欠缺屏蔽消息、去重功能
 */
@Data
@Accessors(chain = true)
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Task extends Thread{

    private TaskInfo taskInfo;

    @Autowired
    private DiscardMessageService discardMessageService;

    @Autowired
    private HandlerHolderProvider holderProvider;


    @Override
    public void run() {

        // 1. 判断是否丢弃消息
        if(discardMessageService.isDiscard(taskInfo)){
            return;
        }

        // 2. 发送消息
        if(CollUtil.isNotEmpty(taskInfo.getReceiver())){
            holderProvider.route(taskInfo.getSendChannel()).doHandler(taskInfo);
        }


    }
}
