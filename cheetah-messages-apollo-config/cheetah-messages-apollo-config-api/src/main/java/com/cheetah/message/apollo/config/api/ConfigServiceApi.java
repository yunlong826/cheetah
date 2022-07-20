package com.cheetah.message.apollo.config.api;

/**
 * 读取配置实现类
 */
public interface ConfigServiceApi {

    String getProperty(String key,String defaultValue);
}
