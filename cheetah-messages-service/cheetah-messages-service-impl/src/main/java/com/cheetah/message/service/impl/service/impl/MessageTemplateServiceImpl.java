package com.cheetah.message.service.impl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cheetah.message.common.dto.MessageTemplate;
import com.cheetah.message.service.impl.mapper.MessageTemplateMapper;
import com.cheetah.message.service.impl.service.MessageTemplateService;
import org.springframework.stereotype.Service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/17 22:37
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate>
        implements MessageTemplateService {
}
