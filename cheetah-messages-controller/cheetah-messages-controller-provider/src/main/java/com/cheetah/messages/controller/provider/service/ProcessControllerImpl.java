package com.cheetah.messages.controller.provider.service;

import com.cheetah.messages.controller.api.ProcessController;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.cheetah.messages.controller.api.pipeline.ProcessTemplate;
import org.apache.dubbo.config.annotation.Service;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 18:25
 */
@Service
public class ProcessControllerImpl implements ProcessController {
    @Override
    public ProcessContext process(ProcessContext context) {
        return null;
    }

    @Override
    public Map<String, ProcessTemplate> getTemplateConfig() {
        return null;
    }
}
