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
> ***
>关闭对外所有服务，为保证能够接收请求，随便开放一个服务来接收请求，目的是保证我们的zuul可以使用。

> 在webmaster服务中上传我们开放的测试接口，测试接口一定要注册到eureka中，否则在网关中找不到其服务，然后在我们的网关中利用前置过滤器，
>根据前端传来的apiname查找redis缓存中开放的接口数据，查询成功后继续查询serviceId以及url，然后利用zuul进行服务的跳转，未查询到数据则返回错误信息
- 端口：31000