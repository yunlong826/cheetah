package com.cheetah.message.handler.provider.service;

import cn.hutool.core.collection.CollUtil;
import com.cheetah.message.common.domain.AnchorInfo;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.common.enums.AnchorState;
import com.cheetah.message.handler.api.HandlerHolderApi;
import com.cheetah.message.handler.api.TaskPendingHolderApi;
import com.cheetah.message.handler.api.consume.ConsumeService;
import com.cheetah.message.handler.provider.pending.Task;
import com.cheetah.message.handler.provider.utils.GroupIdMappingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.List;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 21:44
 */
@Service
@Slf4j
public class ConsumeServiceProvider implements ConsumeService {

    @Autowired
    private ApplicationContext context;

    @Reference
    private TaskPendingHolderApi taskPendingHolder;



    @Reference
    private HandlerHolderApi handlerHolder;

    @Override
    public void consume2Send(List<TaskInfo> taskInfoLists) {
        String topicGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(CollUtil.getFirst(taskInfoLists.iterator()));
        for (TaskInfo taskInfo : taskInfoLists) {
            log.info("日志埋点信息:------>{}",AnchorInfo.builder()
                    .ids(taskInfo.getReceiver())
                    .businessId(taskInfo.getBusinessId())
                    .state(AnchorState.RECEIVE.getCode())
                    .build());
            Task task = context.getBean(Task.class).setTaskInfo(taskInfo);
            taskPendingHolder.route(topicGroupId).execute(task);
        }
    }

    @Override
    public void consume2recall(MessageTemplate messageTemplate) {
        handlerHolder.route(messageTemplate.getSendChannel()).recall(messageTemplate);
    }
}
