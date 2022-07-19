package com.cheetah.message.cron.pending;

import com.cheetah.message.cron.vo.CrowdInfoVo;
import com.cheetah.message.send.api.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:36
 */
@Slf4j
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CrowdBatchTaskPending extends AbstractLazyPending<CrowdInfoVo>{
    @Reference
    private SendService sendService;

    public CrowdBatchTaskPending(){

    }

    @Override
    public void pending(CrowdInfoVo crowdInfoVo) {

    }

    @Override
    public void setStop(boolean b) {

    }
}
