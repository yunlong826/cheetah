package com.cheetah.message.cron.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * Description: 每一行csv的记录
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/19 21:31
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CrowdInfoVo implements Serializable {
    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 接收者id
     */
    private String receiver;

    /**
     * 参数信息
     */
    private Map<String, String> params;
}
