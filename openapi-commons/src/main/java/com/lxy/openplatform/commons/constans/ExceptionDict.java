package com.lxy.openplatform.commons.constans;

public interface ExceptionDict {
    //内容为空的异常错误码
    String CONTENT_NULL_EXCEPTION="10001";
    String ROUTING_ERROR = "20001"; //路由错误的异常
    String SYSTEMPARAM_MISSED = "20002"; //系统公共参数没有传递
    String SYSTEMPARAM_TIME_STAMP_ERROR = "20003"; //时间戳不对
    String SYSTEMPARAM_SIGN_ERROR = "20004"; //签名校验失败
    String SYSTEMPARAM_IDEMPOTENTS_ERROR = "20005"; //签名校验失败
    String LIMIT_ERROR = "20006"; //免费接口的使用次数已经完成
    String FEE_ERROR = "20007"; //没钱了
    String UNLOGIN = "20008"; //没认证
}
