package com.cheetah.message.handler.provider.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cheetah.message.apollo.config.api.ConfigServiceApi;
import com.cheetah.message.common.constant.AustinConstant;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Component;

/**
 * Description: 获取账户信息工具类
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 19:08
 */
@Component
public class AccountUtils {

    @Reference
    private ConfigServiceApi config;


    /**
     * * (key:dingDingRobotAccount) 钉钉自定义机器人参数示例：
     * [{"ding_ding_robot_10":{"secret":"SEC9222d4768aded74114faae92229de422222fedf",
     * "webhook":"https://oapi.dingtalk.com/robot/send?access_token=8d03b6442222203d87333367328b0c3003d164715d2c6c6e56"}}]
     *
     * @param sendAccount
     * @param apolloKey
     * @param prefix
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getAccount(Integer sendAccount, String apolloKey, String prefix, Class<T> clazz) {
        String accountValues = config.getProperty(apolloKey, AustinConstant.APOLLO_DEFAULT_VALUE_JSON_ARRAY);
        JSONArray jsonArray = JSON.parseArray(accountValues);
        for(int i = 0;i < jsonArray.size(); i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            T object = jsonObject.getObject(prefix+sendAccount,clazz);
            if(object != null){
                return object;
            }
        }
        return null;
    }
}
