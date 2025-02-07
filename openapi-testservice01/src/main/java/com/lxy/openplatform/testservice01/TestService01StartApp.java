package com.lxy.openplatform.testservice01;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TestService01StartApp {

    public static void main (String[] args){
        SpringApplication.run(TestService01StartApp.class,args);
    }
}
