package com.cheetah.message.handler.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.cheetah.message.handler.provider")
public class CheetahMessagesHandlerProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheetahMessagesHandlerProviderApplication.class, args);
    }

}
