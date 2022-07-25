package com.cheetah.message.dtp.provider;


import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo(scanBasePackages = "com.cheetah.message.dtp.provider")
public class CheetahMessagesDtpProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(CheetahMessagesDtpProviderApplication.class, args);
    }

}
