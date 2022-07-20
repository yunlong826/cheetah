package com.cheetah.message.apollo.config.provider.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import com.cheetah.message.apollo.config.api.ConfigServiceApi;
import com.ctrip.framework.apollo.Config;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

import java.nio.charset.StandardCharsets;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/20 20:29
 */
@Service
public class ConfigServiceImpl implements ConfigServiceApi {

    /**
     * 本地配置
     */
    private static final String PROPERTIES_PATH = "local.properties";
    private Props props = new Props(PROPERTIES_PATH, StandardCharsets.UTF_8);

    /**
     * apollo配置
     */
    @Value("${apollo.bootstrap.enabled}")
    private Boolean enableApollo;
    @Value("${apollo.bootstrap.namespaces}")
    private String namespaces;


    @Override
    public String getProperty(String key, String defaultValue) {
        if (enableApollo) {
            Config config = com.ctrip.framework.apollo.ConfigService.getConfig(namespaces.split(StrUtil.COMMA)[0]);
            return config.getProperty(key, defaultValue);
        } else {
            return props.getProperty(key, defaultValue);
        }
    }
}
