package com.skeleton.outside.service.provider.config;// package com.micro.demo1.provider.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxhsea
 */
@Configuration
@Slf4j
public class FeignConfiguration implements RequestInterceptor {

//    @Value("${outside.url}")
    private String outsideUrl;

//    @Value("${outside.access-token}")
    private String outsideToken;

    private static final String ACCESS_TOKEN = "access_token";

    @Override
    public void apply(RequestTemplate template) {
        template.target(outsideUrl);
        template.query(ACCESS_TOKEN, outsideToken);
    }
}
