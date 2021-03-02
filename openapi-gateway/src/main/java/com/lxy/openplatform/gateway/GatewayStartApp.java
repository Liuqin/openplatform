package com.lxy.openplatform.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author Lvxy
 * @Date 2021/3/2 18:19
 * @Version 1.0
 * @Desc
 */

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableCircuitBreaker
@EnableFeignClients
public class GatewayStartApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayStartApp.class,args);
    }
}
