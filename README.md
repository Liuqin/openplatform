# openplatform
>开放平台

## 1.eureka模块
>eureka服务器  
- 端口:20000
- 注册中心地址:http://localhost:20000/eureka  
- 密码:admin
- 址进行放行:/eureka/**
## 2.config模块
>config server统一配置中心，为其他模块提供配置
- 端口:12000
- git服务器仓库地址:https://gitee.com/l-960/config_resp
- 手动刷新配置服务器:rabbitmq
- 手动刷新地址：http://localhost:12000/actuator/bus-refresh
## 3.cache模块
> 使用redis缓存，自定义redisTemplate序列化方式，编写redis服务接口，对外提供http服务
- 端口：21000
## 4.webmaster模块
> 将以前编写的ssm项目进行整合，改成微服务项目。
- 端口：8080
- 缓存接口：ApiMappingServiceImpl路由管理
- 缓存接口：ApiSystemparamServiceImpl系统参数管理
- 缓存接口：AppInfoServiceImpl应用管理
- 缓存接口：CustomerServiceImpl客户管理
## 4.zuul模块