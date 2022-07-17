package com.cheetah.messages.controller.api.pipeline;

import java.util.List;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 业务执行模板（把责任链的逻辑串起来）
 * @date 2022/7/17 18:29
 */
public class ProcessTemplate {
    private List<BusinessProcess> processList;

    public List<BusinessProcess> getProcessList() {
        return processList;
    }
    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }
}
