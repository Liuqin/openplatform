package com.lxy.openplatform.cache.controller;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.lxy.openplatform.cache.service.CacheService;
import com.lxy.openplatform.cache.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/cache")
public class CacheController {

    // 使用自带的slf4j日志
    private static Logger logger = LoggerFactory.getLogger(CacheController.class);

    @Autowired
    private CacheService cacheService;


    @PostMapping("/set/{key}/{value}/{expireTime}")
    //降级的配置
    @HystrixCommand(fallbackMethod = "save2RedisFallback")
    public boolean save2Redis(@PathVariable String key, @PathVariable String value, @PathVariable long expireTime) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(value);

        return cacheService.save2Redis(key, value, expireTime);
    }


    @GetMapping("/get/{key}")
    //降级的配置
    @HystrixCommand(fallbackMethod = "getFromRedisFallback")
    public String getFromRedis(@PathVariable String key) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.getFromRedis(key);
    }

    @PostMapping("/delete/{key}")
    public boolean deleteKey(@PathVariable String key) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.deleteKey(key);
    }

    @PostMapping("/expire/{key}/{expireTime}")
    public boolean expire(@PathVariable String key, @PathVariable long expireTime) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.expire(key, expireTime);
    }


    @GetMapping("/getid/{key}")
    public Long getAutoIncrementId(@PathVariable String key) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.getAutoIncrementId(key);
    }

    @GetMapping("/smembers/{key}")
    public Set<Object> sMembers(@PathVariable String key) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.sMembers(key);
    }

    @PostMapping("/sadd/{key}/{member}")
    public Long sAdd(@PathVariable String key, @PathVariable String member) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(member);
        return cacheService.sAdd(key, member);
    }

    @PostMapping("/sadds/{key}")
    public Long sAdd(@PathVariable String key, String[] members) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.sAdd(key, members);
    }


    @PostMapping("/sremove/{key}/{member}")
    public Long sRemove(@PathVariable String key, @PathVariable String member) throws Exception {

        RedisUtil.checkNull(key);
        RedisUtil.checkNull(member);
        return cacheService.sRemove(key, member);
    }


    @PostMapping("/hset/{key}/{field}/{value}")
    public boolean hSet(@PathVariable String key, @PathVariable String field, @PathVariable String value) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(field);
        RedisUtil.checkNull(value);
        return cacheService.hSet(key, field, value);
    }

    @GetMapping("/hget/{key}/{field}")
    public String hGet(@PathVariable String key, @PathVariable String field) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(field);
        return cacheService.hGet(key, field);
    }


    @GetMapping("/hgetall/{key}")
    public Map<Object, Object> hGetAll(@PathVariable String key) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.hGetAll(key);
    }

    @PostMapping("/hmset/{key}")
    public boolean hMSet(@PathVariable String key, @RequestBody Map<Object, Object> values) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.hMSet(key, values);
    }


    @GetMapping("/keys/{partten}")
    public Set<String> findKeyByPartten(@PathVariable String partten) throws Exception {
        RedisUtil.checkNull(partten);
        return cacheService.findKeyByPartten(partten);
    }

    @GetMapping("/increment/{key}/{delta}")
    public Long getAutoIncrementId(@PathVariable String key, @PathVariable long delta) throws Exception {
        RedisUtil.checkNull(key);
        return cacheService.getAutoIncrementId(key, delta);
    }

    @GetMapping("/hIncrementid/{key}/{field}/{delta}")
    public Long hIncrementId(@PathVariable String key, @PathVariable String field, @PathVariable long delta) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(field);
        return cacheService.hIncrementId(key, field, delta);
    }

    @PostMapping("/setnx/{key}/{value}/{expireTime}")
    public boolean setNx(@PathVariable String key, @PathVariable String value, @PathVariable long expireTime) throws Exception {
        RedisUtil.checkNull(key);
        RedisUtil.checkNull(value);
        return cacheService.setNx(key, value, expireTime);
    }

    /**
     * 保存数据到缓存中的降级的方法
     * 重复性工作 先写一部分 剩下的后续想起来补上
     * @param key
     * @param value
     * @param expireTime
     * @return
     * @throws Exception
     */
    public boolean save2RedisFallback(String key, String value, long expireTime) throws Exception {
        logger.error("save2Redis 出现异常,进行降级,{}:{}",key,value);
        return false;
    }


    @HystrixCommand(fallbackMethod = "getFromRedisFallback")
    public String getFromRedisFallback(String key) throws Exception {
        logger.error("getFromRedi 出现异常,进行降级,{}",key);
        return null;
    }
}
