package com.cheetah.message.cron.pending;

import com.cheetah.message.cron.vo.CrowdInfoVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Description: 延迟消费 阻塞队列-消费者和生产者实现
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:36
 */
@Slf4j
@Data
public abstract class AbstractLazyPending<T> {
    public abstract void pending(CrowdInfoVo crowdInfoVo);

    public abstract void setStop(boolean b);
}
