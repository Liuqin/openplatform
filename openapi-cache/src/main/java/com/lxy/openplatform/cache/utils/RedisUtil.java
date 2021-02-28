package com.lxy.openplatform.cache.utils;

import com.lxy.openplatform.cache.exception.RedisException;
import com.lxy.openplatform.commons.constans.ExceptionDict;
import org.springframework.util.StringUtils;

public class RedisUtil {
    /**
     * 用于判断指定内容是不是为空
     * @param source
     */
    public static void checkNull(String source) {
        if (StringUtils.isEmpty(source)) {
            throw  new RedisException("参数为空", ExceptionDict.CONTENT_NULL_EXCEPTION);
        }
    }
}
