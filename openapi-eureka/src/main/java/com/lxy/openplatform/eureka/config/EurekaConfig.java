package com.lxy.openplatform.eureka.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author Lvxy
 * @Date 2021/2/25 0:15
 * @Version 1.0
 * @Desc 我们的eureka的配置,防止无法注册服务
 */
@EnableWebSecurity
public class EurekaConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//禁用掉csrf防攻击,防止我们的服务无法注册
                .authorizeRequests()//需要认证所有的请求
                .mvcMatchers("/eureka/**").permitAll()//地址放行，无需密码 放行注册中心
                .mvcMatchers("/actuator/**").permitAll()
                .anyRequest().authenticated().and().httpBasic();
    }
}