package com.cheetah.message.handler.provider.service.config.impl;

import cn.hutool.setting.dialect.Props;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.cheetah.message.handler.provider.service.config.ConfigService;
import com.cheetah.message.handler.provider.utils.StringToPropertiesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

/**
 * Description:
 *
 * @author longyun
 * @version 1.0
 * @date 2022/7/24 12:53
 */
@Service
@Slf4j
public class ConfigServiceImpl implements ConfigService {

    /**
     * 本地配置
     */
    private static final String PROPERTIES_PATH = "local.properties";
    private Props props = new Props(PROPERTIES_PATH, StandardCharsets.UTF_8);


    /**
     * nacos配置(针对)
     */
    @NacosValue(value = "${messages.account.bootstrap.enable}",autoRefreshed = true)
    private Boolean enableNacos;

    @NacosValue(value = "${messages.account.data-id}",autoRefreshed = true)
    private String data_ids;

    @NacosValue(value = "${messages.account.group}",autoRefreshed = true)
    private String group;

    @NacosValue(value = "${messages.account.server-addr}",autoRefreshed = true)
    private String serverAddr;


    private com.alibaba.nacos.api.config.ConfigService configService;

    @Override
    public String getProperty(String key, String defaultValue) {
        if(enableNacos){
            this.configService = getConfigService();
            Map<String,String> properties = null;
            try{
                String config = configService.getConfig(data_ids, group, 3000);
                log.info("com.alibaba.nacos.api.config.ConfigService configService====={}",config);
                properties = StringToPropertiesUtils.stringToMap(config);
            }catch (NacosException e){
                e.printStackTrace();
            }
            boolean b = properties.containsKey(key);
            return properties.getOrDefault(key,defaultValue);
        }else{
            return props.getProperty(key,defaultValue);
        }
    }

    private com.alibaba.nacos.api.config.ConfigService getConfigService(){
        Properties properties = new Properties();
        properties.put("serverAddr",serverAddr);
        com.alibaba.nacos.api.config.ConfigService configService = null;
        try {
            configService = NacosFactory.createConfigService(properties);
        } catch (NacosException nacosException) {
            nacosException.printStackTrace();
        }
        return configService;
    }
}
