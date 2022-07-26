package com.cheetah.message.service.impl;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.cheetah.message.service.impl")
public class CheetahMessagesServiceImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheetahMessagesServiceImplApplication.class, args);
    }

}
