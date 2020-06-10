package com.skeleton.order.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yxhsea
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@ImportResource("classpath:applicationContext.xml")
public class OrderServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceRunner.class, args);
    }
}
