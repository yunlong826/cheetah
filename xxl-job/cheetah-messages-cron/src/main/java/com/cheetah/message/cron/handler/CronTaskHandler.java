package com.cheetah.message.cron.handler;

import com.cheetah.message.cron.config.CronAsyncThreadPoolConfig;
import com.cheetah.message.cron.service.TaskHandler;
import com.cheetah.message.dtp.api.ThreadPoolUtilsApi;
import com.dtp.core.thread.DtpExecutor;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: 后台提交的定时任务处理类
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:21
 */
@Service
@Slf4j
public class CronTaskHandler {
    @Autowired
    private TaskHandler taskHandler;

    @Reference
    private ThreadPoolUtilsApi threadPoolUtils;

    private DtpExecutor dtpExecutor = CronAsyncThreadPoolConfig.getXxlCronExecutor();

    /**
     * 处理后台的 cheetah 定时任务消息
     */
    @XxlJob("cheetahJob")
    public void execute() {
        log.info("CronTaskHandler#execute messageTemplateId:{} cron exec!", XxlJobHelper.getJobParam());
        threadPoolUtils.register(dtpExecutor);

        Long messageTemplateId = Long.valueOf(XxlJobHelper.getJobParam());
        dtpExecutor.execute(() -> taskHandler.handle(messageTemplateId));

    }
}
