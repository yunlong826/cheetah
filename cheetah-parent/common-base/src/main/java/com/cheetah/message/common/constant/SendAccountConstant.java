package com.cheetah.message.common.constant;

/**
 * 发送账号的常量信息汇总
 * <p>
 * (读取apollo的key和前缀)
 * <p>
 * 约定：所有的账号都从10开始，步长为10
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 19:13
 */
public class SendAccountConstant {
    /**
     * 账号约定：所有的账号都从10开始，步长为10
     */
    public static final Integer START = 10;
    public static final Integer STEP = 10;

    /**
     * 钉钉群自定义机器人 账号
     */
    public static final String DING_DING_ROBOT_ACCOUNT_KEY = "dingDingRobotAccount";
    public static final String DING_DING_ROBOT_PREFIX = "ding_ding_robot_";
}
