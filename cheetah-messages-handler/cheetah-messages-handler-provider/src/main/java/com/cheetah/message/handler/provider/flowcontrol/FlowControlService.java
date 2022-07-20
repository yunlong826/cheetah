package com.cheetah.message.handler.provider.flowcontrol;


import com.cheetah.message.common.domain.TaskInfo;

/**
 * 流量控制服务
 */
public interface FlowControlService {
    /**
     * 根据渠道进行流量控制
     *
     * @param taskInfo
     * @param flowControlParam
     */
    void flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam);
}
