package com.skeleton.outside.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yxhsea
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource("classpath:applicationContext.xml")
@EnableFeignClients
public class OutsideServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(OutsideServiceRunner.class, args);
    }
}
