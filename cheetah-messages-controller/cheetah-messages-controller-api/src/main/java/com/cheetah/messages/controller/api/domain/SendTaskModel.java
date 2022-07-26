package com.cheetah.messages.controller.api.domain;

import com.cheetah.message.common.domain.MessageParam;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.messages.controller.api.pipeline.ProcessModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送消息任务模型
 * @date 2022/7/17 19:05
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendTaskModel implements ProcessModel,Serializable{
    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 请求参数
     */
    private List<MessageParam> messageParamList;

    /**
     * 发送任务的信息
     */
    private List<TaskInfo> taskInfo;

    /**
     * 撤回任务的信息
     */
    private MessageTemplate messageTemplate;
}
