package com.skeleton.user.service.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author yxhsea
 */
@SpringBootApplication
@ImportResource("classpath:applicationContext.xml")
public class UserServiceRunner {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceRunner.class, args);
    }
}
