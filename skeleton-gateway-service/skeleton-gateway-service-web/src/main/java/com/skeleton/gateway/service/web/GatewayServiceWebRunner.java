package com.skeleton.gateway.service.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yxhsea
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ComponentScan(basePackages = {"com.skeleton.**"})
@ImportResource("classpath:applicationContext.xml")
public class GatewayServiceWebRunner {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceWebRunner.class, args);
    }
}
