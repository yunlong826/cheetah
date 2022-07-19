package com.cheetah.message.cron.handler;

import com.cheetah.message.cron.service.TaskHandler;
import groovy.util.logging.Slf4j;
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
}
