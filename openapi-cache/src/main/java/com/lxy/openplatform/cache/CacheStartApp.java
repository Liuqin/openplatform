package com.lxy.openplatform.cache;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Lvxy
 * @Date 2021/2/25 19:30
 * @Version 1.0
 * @Desc
 */
@SpringBootApplication
// config客户端无需配置注解即可使用
@EnableEurekaClient
// @EnableDiscoveryClient 开启服务注册，想像eureka中注册当前服务，可以忽略编写，在使用eureka的情况下等效于EnableEurekaClient
@EnableCircuitBreaker // 开启断路器
public class CacheStartApp {
    public static void main (String[] args){
        SpringApplication.run(CacheStartApp.class,args);
    }
}

