package com.cheetah.message.handler.provider.service.config;


/**
 * 读取消息账号的实现类
 */
public interface ConfigService {
    String getProperty(String key,String defaultValue);
}
