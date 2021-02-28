package com.lxy.openplatform.commons.constans;

public interface SystemParams {

    String METHOD_REDIS_PRE="APINAME:";//路由关系映射在redis中的key的前缀
    String SYSYTEMPARAMS="SYSTEMPARAMS:KEYS";//我们请求中要求用户必须传递的系统参数在redis中的key
    String APPKEY_REDIS_PRE = "APPKEY:";//应用信息在redis中的key的前缀
    String CUSTOMER_REDIS_PRE = "APICUSTOMER:";//客户信息在redis中的key的前缀
    String IDEMPOTENTS_REDIS_PRE = "IDEMPOTENTS:";//幂等性的请求在redis中的前缀
    String JWT_TOKEN_REDIS_PRE = "TOKEN:";//用户登陆后的token在redis中的最新值
}
