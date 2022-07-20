package com.cheetah.message.handler.provider.domain.dingding;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * 钉钉群 自定义机器人返回的结果
 * <p>
 * 正常的返回：{"errcode":0,"errmsg":"ok"}
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 19:24
 */
@NoArgsConstructor
@Data
@AllArgsConstructor
public class DingDingRobotResult {
    /**
     * errcode
     */
    @SerializedName("errcode")
    private Integer errCode;

    /**
     * errmsg
     */
    @SerializedName("errmsg")
    private String errMsg;
}
