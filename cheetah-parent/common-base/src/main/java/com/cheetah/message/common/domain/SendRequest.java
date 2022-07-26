package com.cheetah.message.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送/撤回接口的参数
 * @date 2022/7/17 17:10
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SendRequest implements Serializable {

    /**
     * 执行业务类型
     * send:发送消息
     * recall:撤回消息
     */
    private String code;

    /**
     * 消息模板Id
     * 【必填】
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 当业务类型为"send"，必传
     */
    private MessageParam messageParam;
}
