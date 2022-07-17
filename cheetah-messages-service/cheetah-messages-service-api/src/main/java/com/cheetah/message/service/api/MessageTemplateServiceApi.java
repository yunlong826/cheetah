package com.cheetah.message.service.api;

import com.cheetah.message.common.dto.MessageTemplate;

/**
 * @author jack_yun
 * @version 1.0
 * @description:  messageTemplate crud 接口
 * @date 2022/7/17 22:25
 */
public interface MessageTemplateServiceApi {
    MessageTemplate findById(Long id);
}
