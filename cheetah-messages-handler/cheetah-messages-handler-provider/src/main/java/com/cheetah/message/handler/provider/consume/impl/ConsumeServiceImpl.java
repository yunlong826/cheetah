package com.cheetah.message.handler.provider.consume.impl;

import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.handler.provider.consume.ConsumeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO 消息消费与撤回，后续会通过handler进行统一消息调度，由于项目是微服务，后续实现。
 * @date 2022/7/18 20:02
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {
    private static final String LOG_BIZ_TYPE = "Receiver#consumer";
    private static final String LOG_BIZ_RECALL_TYPE = "Receiver#recall";


    @Override
    public void consume2Send(List<TaskInfo> taskInfoLists) {

    }

    @Override
    public void consume2recall(MessageTemplate messageTemplate) {

    }
}
