package com.cheetah.message.handler.provider.flowcontrol.impl;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.handler.provider.flowcontrol.FlowControlParam;
import com.cheetah.message.handler.provider.flowcontrol.FlowControlService;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 18:50
 */
@Service
public class FlowControlServiceImpl implements FlowControlService {
    private static final String FLOW_CONTROL_KEY = "flowControlRule";
    private static final String FLOW_CONTROL_PREFIX = "flow_control_";
    @Override
    public void flowControl(TaskInfo taskInfo, FlowControlParam flowControlParam) {

    }
}
