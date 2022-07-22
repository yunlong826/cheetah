package com.cheetah.message.common.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 23:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsContentModel extends ContentModel{

    /**
     * 短信发送内容
     */
    private String content;

    /**
     * 短信发送链接
     */
    private String url;
}
