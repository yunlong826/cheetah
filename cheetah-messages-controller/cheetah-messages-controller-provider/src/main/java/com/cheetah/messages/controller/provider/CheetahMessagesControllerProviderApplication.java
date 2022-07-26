package com.cheetah.messages.controller.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.cheetah.messages.controller.provider")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
public class CheetahMessagesControllerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheetahMessagesControllerProviderApplication.class, args);
    }

}
