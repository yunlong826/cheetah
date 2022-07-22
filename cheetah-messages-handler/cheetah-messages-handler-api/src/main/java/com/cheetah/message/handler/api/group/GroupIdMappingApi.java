package com.cheetah.message.handler.api.group;

import com.cheetah.message.common.domain.TaskInfo;

import java.util.List;

public interface GroupIdMappingApi {

    String getGroupIdByTaskInfo(TaskInfo taskInfo);

    List<String> getAllGroupIds();
}
