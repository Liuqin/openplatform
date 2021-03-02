package com.lxy.openapi.web.master;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.lxy.openapi.web.master.mapper")
@EnableEurekaClient
@EnableFeignClients
@EnableTransactionManagement //开启事务管理
public class WebMasterStartApp {
    public static void main (String[] args){
        SpringApplication.run(WebMasterStartApp.class,args);
    }
}
