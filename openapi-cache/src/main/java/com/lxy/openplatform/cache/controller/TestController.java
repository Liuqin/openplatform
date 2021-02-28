package com.lxy.openplatform.cache.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author Lvxy
 * @Date 2021/2/25 19:35
 * @Version 1.0
 * @Desc
 */
@RestController()
@RefreshScope
public class TestController {

    @Value("${env}")
    private String env;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @RequestMapping("/test")
    public String test(){
        System.out.println(env);
        return env;
    }
    @RequestMapping("/redis")
    public String test2(){
        redisTemplate.opsForValue().set("name","吕星宇",10, TimeUnit.SECONDS);
        return "ok";

    }



}
