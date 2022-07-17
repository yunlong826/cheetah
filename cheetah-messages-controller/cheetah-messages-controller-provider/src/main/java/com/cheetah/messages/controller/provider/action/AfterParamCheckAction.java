package com.cheetah.messages.controller.provider.action;

import com.cheetah.messages.controller.api.pipeline.BusinessProcess;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.cheetah.messages.controller.provider.domain.SendTaskModel;
import org.springframework.stereotype.Service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/17 19:52
 */
@Service
public class AfterParamCheckAction implements BusinessProcess<SendTaskModel> {
    @Override
    public void process(ProcessContext<SendTaskModel> context) {

    }
}
