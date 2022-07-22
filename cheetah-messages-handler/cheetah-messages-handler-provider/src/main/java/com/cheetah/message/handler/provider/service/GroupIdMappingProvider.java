package com.cheetah.message.handler.provider.service;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.handler.api.group.GroupIdMappingApi;
import com.cheetah.message.handler.provider.utils.GroupIdMappingUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/21 21:23
 */
@Service
public class GroupIdMappingProvider implements GroupIdMappingApi {
    @Override
    public String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        return GroupIdMappingUtils.getGroupIdByTaskInfo(taskInfo);
    }

    @Override
    public List<String> getAllGroupIds() {
        return GroupIdMappingUtils.getAllGroupIds();
    }
}
