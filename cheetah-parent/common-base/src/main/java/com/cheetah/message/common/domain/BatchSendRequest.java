package com.cheetah.message.common.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送接口的参数
 * @date 2022/7/17 17:27
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BatchSendRequest implements Serializable {

    /**
     * 执行业务类型
     * 必传,参考 BusinessCode枚举
     */
    private String code;


    /**
     * 消息模板Id
     * 必传
     */
    private Long messageTemplateId;


    /**
     * 消息相关的参数
     * 必传
     */
    private List<MessageParam> messageParamList;
}
