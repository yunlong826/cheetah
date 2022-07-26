package com.cheetah.message.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 发送接口返回值
 * @date 2022/7/17 17:27
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SendResponse implements Serializable {
    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;
}
