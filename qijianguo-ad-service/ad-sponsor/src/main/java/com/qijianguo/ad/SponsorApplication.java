package com.qijianguo.ad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableFeignClients   // 可以在 微服务中调用其他微服务，在这儿只是为了在。。中实现 监控
// @ExableEurekaClient   // 标识是一个eureka client服务， 从注册中心拿到微服务
@EnableCircuitBreaker // 断路器， 实现监控
@EnableSwagger2
public class SponsorApplication {

    public static void main(String[] args) {
        SpringApplication.run(SponsorApplication.class, args);
    }
}
