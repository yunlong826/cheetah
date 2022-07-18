package com.cheetah.messages.controller.provider.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.cheetah.message.common.enums.RespStatusEnum;
import com.cheetah.message.common.vo.BasicResultVO;
import com.cheetah.messages.controller.api.ProcessController;
import com.cheetah.messages.controller.api.pipeline.BusinessProcess;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.cheetah.messages.controller.api.pipeline.ProcessTemplate;
import com.cheetah.messages.controller.provider.config.TemplateConfig;
import com.cheetah.messages.controller.provider.exception.ProcessException;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 18:25
 */
@Service
public class ProcessControllerImpl implements ProcessController {


    @Autowired
    private TemplateConfig templateConfig;

    /**
     * 执行责任链
     *
     * @param context
     * @return 返回上下文内容
     */
    @Override
    public ProcessContext process(ProcessContext context) {
        /**
         * 前置检查
         */
        try{
            preCheck(context);
        }catch (ProcessException e){
            return e.getProcessContext();
        }

        /**
         * 遍历流程节点
         */
        List<BusinessProcess> processList = templateConfig.getTemplateConfig()
                .get(context.getCode()).getProcessList();
        for(BusinessProcess businessProcess:processList){
            businessProcess.process(context);
            if(context.getNeedBreak()){
                break;
            }
        }
        return context;
    }

    /**
     * 执行前检查，出错则抛出异常
     *
     * @param context 执行上下文
     * @throws ProcessException 异常信息
     */
    private void preCheck(ProcessContext context) throws ProcessException{
        // 执行上下文
        if(context == null){
            context = new ProcessContext();
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
            throw new ProcessException(context);
        }

        // 业务代码
        String businessCode = context.getCode();

        if(StrUtil.isBlank(businessCode)){
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板
        ProcessTemplate processTemplate = templateConfig.getTemplateConfig().get(businessCode);
        if(processTemplate == null){
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_TEMPLATE_IS_NULL));
            throw new ProcessException(context);
        }

        // 执行模板列表
        List<BusinessProcess> processList = processTemplate.getProcessList();
        if(CollUtil.isEmpty(processList)){
            context.setResponse(BasicResultVO.fail(RespStatusEnum.PROCESS_LIST_IS_NULL));
            throw new ProcessException(context);
        }
    }

    @Override
    public Map<String, ProcessTemplate> getTemplateConfig() {
        return templateConfig.getTemplateConfig();
    }
}
