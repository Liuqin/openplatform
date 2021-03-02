package com.lxy.openplatform.gateway.filters;


import org.apache.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Lvxy
 * @Date 2021/3/2 19:46
 * @Version 1.0
 * @Desc 请求路径添加前缀：弃用方案
 */
//@Component
//@WebFilter("/*")
public class AjaxFilter implements Filter {

    private static List<String> uriList = new ArrayList<>();

    static {
        uriList.add("/dologin");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        System.out.println("进入ApplicationFilter......");

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

        String requestURI = httpServletRequest.getRequestURI();

        System.out.println(requestURI);

        String targetURI = "/webmaster" + requestURI;

        httpServletRequest.getRequestDispatcher(targetURI).forward(servletRequest, servletResponse);

        //filterChain.doFilter(servletRequest, servletResponse);


    }

    @Override
    public void destroy() {

    }
}
