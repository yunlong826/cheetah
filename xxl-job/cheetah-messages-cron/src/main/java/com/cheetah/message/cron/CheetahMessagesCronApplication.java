package com.cheetah.message.cron;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.cheetah.message.cron")
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class CheetahMessagesCronApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheetahMessagesCronApplication.class, args);
	}

}
