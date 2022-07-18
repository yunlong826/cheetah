package com.cheetah.messages.controller.provider.exception;

import com.cheetah.message.common.enums.RespStatusEnum;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/18 17:56
 */
public class ProcessException extends RuntimeException{
    /**
     * 流程处理上下文
     */
    private final ProcessContext processContext;

    public ProcessException(ProcessContext processContext) {
        super();
        this.processContext = processContext;
    }

    public ProcessException(ProcessContext processContext, Throwable cause) {
        super(cause);
        this.processContext = processContext;
    }

    @Override
    public String getMessage() {
        if (this.processContext != null) {
            return this.processContext.getResponse().getMsg();
        } else {
            return RespStatusEnum.CONTEXT_IS_NULL.getMsg();
        }
    }

    public ProcessContext getProcessContext() {
        return processContext;
    }
}
