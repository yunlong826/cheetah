package com.cheetah.message.cron.api;

import com.cheetah.message.common.vo.BasicResultVO;
import com.cheetah.message.cron.api.entity.XxlJobGroup;
import com.cheetah.message.cron.api.entity.XxlJobInfo;

/**
 * @author jack_yun
 * @version 1.0
 * @description: 定时任务服务
 * @date 2022/7/19 23:43
 */
public interface CronTaskServiceApi {
    /**
     * 新增/修改 定时任务
     * @param xxlJobInfo
     *
     * @return 新增时返回任务Id，修改时无返回
     */
    BasicResultVO saveCronTask(XxlJobInfo xxlJobInfo);

    /**
     * 删除定时任务
     *
     * @param taskId
     * @return BasicResultVO
     */
    BasicResultVO deleteCronTask(Integer taskId);

    /**
     * 启动定时任务
     *
     * @param taskId
     * @return BasicResultVO
     */
    BasicResultVO startCronTask(Integer taskId);


    /**
     * 暂停定时任务
     *
     * @param taskId
     * @return BasicResultVO
     */
    BasicResultVO stopCronTask(Integer taskId);


    /**
     * 得到执行器Id
     * @param appName
     * @param title
     * @return BasicResultVO
     */
    BasicResultVO getGroupId(String appName, String title);

    /**
     * 创建执行器
     * @param xxlJobGroup
     * @return BasicResultVO
     */
    BasicResultVO createGroup(XxlJobGroup xxlJobGroup);
}
