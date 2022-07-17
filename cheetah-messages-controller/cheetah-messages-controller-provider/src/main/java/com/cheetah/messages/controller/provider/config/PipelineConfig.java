package com.cheetah.messages.controller.provider.config;

import com.cheetah.message.common.enums.BusinessCode;
import com.cheetah.messages.controller.api.pipeline.ProcessTemplate;
import com.cheetah.messages.controller.provider.action.AfterParamCheckAction;
import com.cheetah.messages.controller.provider.action.AssembleAction;
import com.cheetah.messages.controller.provider.action.PreParamCheckAction;
import com.cheetah.messages.controller.provider.action.SendMqAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: api层的pipeline配置类
 * @date 2022/7/17 18:55
 */
@Configuration
public class PipelineConfig {


    @Autowired
    private PreParamCheckAction preParamCheckAction;
    @Autowired
    private AssembleAction assembleAction;
    @Autowired
    private AfterParamCheckAction afterParamCheckAction;
    @Autowired
    private SendMqAction sendMqAction;



    /**
     * 普通发送执行流程
     * 1. 前置参数校验
     * 2. 组装参数
     * 3. 后置参数校验
     * 4. 发送消息至MQ
     * @return
     */
    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(preParamCheckAction, assembleAction,
                afterParamCheckAction, sendMqAction));
        return processTemplate;
    }

    /**
     * 消息撤回执行流程
     * 1.组装参数
     * 2.发送MQ
     * @return
     */
    @Bean("recallMessageTemplate")
    public ProcessTemplate recallMessageTemplate() {
        ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(Arrays.asList(assembleAction, sendMqAction));
        return processTemplate;
    }

    /**
     * pipeline流程控制器
     * 后续扩展则加BusinessCode和ProcessTemplate
     * @return
     */
    @Bean
    public TemplateConfig templateConfig(){
        TemplateConfig templateConfig = new TemplateConfig();
        Map<String, ProcessTemplate> templateMap = new HashMap<>(4);
        templateMap.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());
        templateMap.put(BusinessCode.RECALL.getCode(), recallMessageTemplate());
        templateConfig.setTemplateConfig(templateMap);
        return templateConfig;
    }
}
