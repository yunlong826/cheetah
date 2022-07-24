package com.cheetah.message.handler.provider.service.config.impl;

import cn.hutool.setting.dialect.Props;
import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.api.exception.NacosException;
import com.cheetah.message.handler.provider.service.config.ConfigService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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
     * nacos配置
     */
    @NacosValue("${nacos.config.bootstrap.enabled}")
    private Boolean enableNacos;

    @NacosValue("${nacos.config.data-ids}")
    private String data_ids;

    @NacosValue("${nacos.config.group}")
    private String group;

    @Autowired
    private com.alibaba.nacos.api.config.ConfigService configService;

    @Override
    public String getProperty(String key, String defaultValue) {
        if(enableNacos){
            // todo getProperty 后续完善，现在只是实例
            String config="";
            try{
                config = configService.getConfig(data_ids, group, 3000);
                log.info("com.alibaba.nacos.api.config.ConfigService configService====={}",config);
            }catch (NacosException e){
                e.printStackTrace();
            }
            return config;
        }else{
            return props.getProperty(key,defaultValue);
        }
    }
}
