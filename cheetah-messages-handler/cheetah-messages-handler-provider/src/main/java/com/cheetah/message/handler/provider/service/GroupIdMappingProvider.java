package com.cheetah.message.handler.provider.service;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.handler.api.group.GroupIdMappingApi;
import com.cheetah.message.handler.provider.utils.GroupIdMappingUtils;
import org.apache.dubbo.config.annotation.Service;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/21 23:44
 */
@Service
public class GroupIdMappingProvider implements GroupIdMappingApi {



    @Override
    public List<String> getAllGroupIds() {
        return GroupIdMappingUtils.getAllGroupIds();
    }

    @Override
    public String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        return GroupIdMappingUtils.getGroupIdByTaskInfo(taskInfo);
    }
}
