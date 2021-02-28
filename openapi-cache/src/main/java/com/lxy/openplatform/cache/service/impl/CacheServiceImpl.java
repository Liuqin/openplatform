package com.lxy.openplatform.cache.service.impl;

import com.lxy.openplatform.cache.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @Author Lvxy
 * @Date 2021/2/28 19:29
 * @Version 1.0
 * @Desc
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean save2Redis(String key, String value, long expireTime) throws Exception {
        if (expireTime == 0) {//按照我们自己的要求,如果是0 代表是-1
            expireTime = -1;
        } else if (expireTime < -1) { //如果小于-1 代表的是取绝对值
            expireTime = Math.abs(expireTime);
        }
        redisTemplate.opsForValue().set(key, value);
        if (expireTime > 0) {
            //需要设置有效期,时间单位是毫秒值
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }

        return true;
    }

    @Override
    public String getFromRedis(String key) throws Exception {

        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean deleteKey(String key) throws Exception {
        return redisTemplate.delete(key);
    }

    @Override
    public boolean expire(String key, long expireTime) throws Exception {
        if (expireTime == 0) {//按照我们自己的要求,如果是0 代表是-1
            expireTime = -1;
        } else if (expireTime < -1) { //如果小于-1 代表的是取绝对值
            expireTime = Math.abs(expireTime);
        }
        if (expireTime > 0) {
            //代表需要设置有效期
            return redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        } else {
            //代表需要的是持久化数据
            return redisTemplate.persist(key);
        }

    }

    @Override
    public Long getAutoIncrementId(String key) throws Exception {
        return redisTemplate.opsForValue().increment(key);
    }

    @Override
    public Set<Object> sMembers(String key) throws Exception {
        return redisTemplate.opsForSet().members(key);
    }

    @Override
    public Long sAdd(String key, String member) throws Exception {
        return redisTemplate.opsForSet().add(key,member);
    }

    @Override
    public Long sAdd(String key, String... members) throws Exception {
        return redisTemplate.opsForSet().add(key,members);
    }

    @Override
    public Long sRemove(String key, String member) throws Exception {
        return redisTemplate.opsForSet().remove(key,member);
    }

    @Override
    public boolean hSet(String key, String field, String value) throws Exception {
        redisTemplate.opsForHash().put(key,field,value);
        return true;
    }

    @Override
    public String hGet(String key, String field) throws Exception {
        Object o = redisTemplate.opsForHash().get(key, field);
        return o==null?null:o.toString();
    }

    @Override
    public Map<Object, Object> hGetAll(String key) throws Exception {
        return redisTemplate.opsForHash().entries(key);
    }

    @Override
    public boolean hMSet(String key, Map<Object, Object> values) throws Exception {
        redisTemplate.opsForHash().putAll(key,values);
        return true;
    }

    @Override
    public Set<String> findKeyByPartten(String partten) throws Exception {
        return redisTemplate.keys(partten);
    }

    @Override
    public Long getAutoIncrementId(String key, long delta) throws Exception {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    @Override
    public Long hIncrementId(String key, String field, long delta) throws Exception {
        return redisTemplate.opsForHash().increment(key,field,delta);
    }

    @Override
    public boolean setNx(String key, String value, long expireTime) throws Exception {
        if (expireTime == 0) {//按照我们自己的要求,如果是0 代表是-1
            expireTime = -1;
        } else if (expireTime < -1) { //如果小于-1 代表的是取绝对值
            expireTime = Math.abs(expireTime);
        }
        redisTemplate.opsForValue().setIfAbsent(key, value);
        if (expireTime > 0) {
            //需要设置有效期,时间单位是毫秒值
            redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
        }
        return true;
    }
}
