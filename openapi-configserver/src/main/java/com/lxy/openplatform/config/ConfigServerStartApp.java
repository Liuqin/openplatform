package com.lxy.openplatform.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Author Lvxy
 * @Date 2021/2/25 19:04
 * @Version 1.0
 * @Desc
 */
@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
// @EnableDiscoveryClient 开启服务注册，想像eureka中注册当前服务，可以忽略编写，在使用eureka的情况下等效于EnableEurekaClient
public class ConfigServerStartApp {
    public static void main (String[] args){
        SpringApplication.run(ConfigServerStartApp.class,args);
    }
}
