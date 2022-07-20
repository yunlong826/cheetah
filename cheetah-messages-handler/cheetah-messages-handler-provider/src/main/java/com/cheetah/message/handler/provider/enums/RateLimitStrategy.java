package com.cheetah.message.handler.provider.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * Description: 限流枚举
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 18:49
 */
@Getter
@ToString
@AllArgsConstructor
public enum RateLimitStrategy {

    REQUEST_RATE_LIMIT(10, "根据真实请求数限流"),
    SEND_USER_NUM_RATE_LIMIT(20, "根据发送用户数限流"),
    ;

    private Integer code;
    private String description;
}
