package com.cheetah.messages.controller.provider.action;

import com.cheetah.messages.controller.api.pipeline.BusinessProcess;
import com.cheetah.messages.controller.api.pipeline.ProcessContext;
import com.cheetah.messages.controller.provider.domain.SendTaskModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author jack_yun
 * @version 1.0
 * @description: TODO
 * @date 2022/7/17 19:44
 */
@Slf4j
@Service
public class AssembleAction implements BusinessProcess<SendTaskModel> {

    @Override
    public void process(ProcessContext<SendTaskModel> context) {

    }
}
