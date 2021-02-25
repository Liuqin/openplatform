package com.lxy.openplatform.cache.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping("/test")
    public String test(){
        System.out.println(env);
        return env;
    }

}
