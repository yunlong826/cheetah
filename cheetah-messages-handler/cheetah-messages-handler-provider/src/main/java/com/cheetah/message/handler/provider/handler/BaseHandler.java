package com.cheetah.message.handler.provider.handler;

import com.cheetah.message.common.domain.AnchorInfo;
import com.cheetah.message.common.domain.TaskInfo;
import com.cheetah.message.common.enums.AnchorState;
import com.cheetah.message.handler.api.handler.Handler;
import com.cheetah.message.handler.provider.flowcontrol.FlowControlParam;
import com.cheetah.message.handler.provider.flowcontrol.FlowControlService;
import com.cheetah.message.handler.provider.service.HandlerHolderProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 18:47
 */
@Slf4j
public abstract class BaseHandler implements Handler {

    @Autowired
    private HandlerHolderProvider handlerHolderProvider;

    @Autowired
    private FlowControlService flowControlService;

    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;

    /**
     * 限流相关的参数
     * 子类初始化的时候指定
     */
    protected FlowControlParam flowControlParam;

    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init(){
        handlerHolderProvider.putHandler(channelCode,this);
    }

    /**
     * 流量控制
     *
     * @param taskInfo
     */
    public void flowControl(TaskInfo taskInfo) {
        // 只有子类指定了限流参数，才需要限流
        if (flowControlParam != null) {
            flowControlService.flowControl(taskInfo, flowControlParam);
        }
    }

    @Override
    public void doHandler(TaskInfo taskInfo) {
        flowControl(taskInfo);
        if(handler(taskInfo)){
            log.info("埋点信息AnchorInfo:{}",
                    AnchorInfo.builder()
                            .state(AnchorState.SEND_SUCCESS.getCode())
                            .businessId(taskInfo.getBusinessId())
                            .ids(taskInfo.getReceiver())
                            .build());
            return;
        }
        log.info("埋点信息AnchorInfo:{}",
                AnchorInfo.builder()
                        .state(AnchorState.SEND_FAIL.getCode())
                        .businessId(taskInfo.getBusinessId())
                        .ids(taskInfo.getReceiver())
                        .build());
    }

    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);
}
