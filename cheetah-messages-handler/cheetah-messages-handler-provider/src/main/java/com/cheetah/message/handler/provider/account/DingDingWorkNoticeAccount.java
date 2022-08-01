package com.cheetah.message.handler.provider.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 钉钉工作消息 账号信息
 *
 * @author longyun
 * @version 1.0
 * @date 2022/8/1 21:14
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DingDingWorkNoticeAccount {
    /**
     * 应用的唯一标识key。
     */
    private String appKey;

    /**
     * 应用的密钥
     */
    private String appSecret;

    /**
     * 发送消息时使用的微应用的AgentID
     */
    private String agentId;
}
