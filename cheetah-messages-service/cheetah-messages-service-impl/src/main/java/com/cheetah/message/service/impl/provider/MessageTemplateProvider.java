package com.cheetah.message.service.impl.provider;

import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.service.api.MessageTemplateServiceApi;
import com.cheetah.message.service.impl.service.MessageTemplateService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 22:26
 */
@Service
public class MessageTemplateProvider implements MessageTemplateServiceApi {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Override
    public MessageTemplate findById(Long id) {
        return messageTemplateService.getById(id);
    }
}
