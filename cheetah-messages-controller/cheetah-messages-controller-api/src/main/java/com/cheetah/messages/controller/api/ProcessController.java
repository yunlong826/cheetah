package com.cheetah.messages.controller.api;

import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.cheetah.messages.controller.api.pipeline.ProcessTemplate;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 流程控制器接口
 * @date 2022/7/17 18:10
 */
public interface ProcessController {
    /**
     * 执行责任链
     *
     * @param context
     * @return 返回上下文内容
     */

    public ProcessContext process(ProcessContext context);


    /**
     * 得到模板映射
     *
     * @param
     * @return 模板映射
     */

    public Map<String, ProcessTemplate> getTemplateConfig();

}
