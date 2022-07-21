package com.cheetah.message.handler.api.group;

import com.cheetah.message.common.domain.TaskInfo;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/21 23:43
 */
public interface GroupIdMappingApi {
    List<String> getAllGroupIds();

    String getGroupIdByTaskInfo(TaskInfo taskInfo);
}
