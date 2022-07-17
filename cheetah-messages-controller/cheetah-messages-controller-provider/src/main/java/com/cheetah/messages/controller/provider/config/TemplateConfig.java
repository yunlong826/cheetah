package com.cheetah.messages.controller.provider.config;

import com.cheetah.messages.controller.api.pipeline.ProcessTemplate;
import lombok.Data;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 模板配置类
 * @date 2022/7/17 18:58
 */
@Data
public class TemplateConfig {
    private Map<String, ProcessTemplate> templateConfig = null;
}
