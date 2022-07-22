package com.cheetah.message.common.dto.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author jack_yun
 * @version 1.0
 * @description:
 * @date 2022/7/17 23:54
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MiniProgramContentModel extends ContentModel{
    /**
     * 模板消息发送的数据
     */
    Map<String, String> map;
}
