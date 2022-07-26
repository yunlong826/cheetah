package com.cheetah.message.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.cheetah.message.provider")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CheetahSendProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheetahSendProviderApplication.class, args);
    }

}
