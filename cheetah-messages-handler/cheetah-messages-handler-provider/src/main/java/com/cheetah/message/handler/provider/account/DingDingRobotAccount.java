package com.cheetah.message.handler.provider.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description: 钉钉自定义机器人 账号信息
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 19:11
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DingDingRobotAccount {
    /**
     * 密钥
     */
    private String secret;

    /**
     * 自定义群机器人中的 webhook
     */
    private String webhook;
}
