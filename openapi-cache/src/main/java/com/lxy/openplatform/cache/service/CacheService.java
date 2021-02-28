package com.lxy.openplatform.cache.service;

import java.util.Map;
import java.util.Set;

/**
 * @Author Lvxy
 * @Date 2021/2/28 18:41
 * @Version 1.0
 * @Desc
 */
public interface CacheService {

    /**
     * 向redis中保存字符串类型的数据
     * @param key
     * @param value
     * @param expireTime 有效期, 如果是-1代表永久, 其他负数按照我们自己定义的要求,取绝对值,0代表-1,时间单位是毫秒值
     * @return 成功返回true
     * @throws Exception
     */
    boolean save2Redis(String key, String value, long expireTime) throws Exception;

    /**
     * 从redis中查询String类型的数据
     * @param key
     * @return
     * @throws Exception
     */
    String getFromRedis(String key) throws Exception;

    /**
     * 从redis中删除指定的key
     * @param key
     * @return
     * @throws Exception
     */
    boolean deleteKey(String key) throws Exception;

    /**
     * 设置过期时间
     * @param key
     * @param expireTime 有效期, 如果是-1代表永久, 其他负数按照我们自己定义的要求,取绝对值,0代表-1
     * @return
     * @throws Exception
     */
    boolean expire(String key, long expireTime) throws Exception;

    /**
     * 获取一个自增的数字
     * @param key
     * @return
     * @throws Exception
     */
    Long getAutoIncrementId(String key) throws Exception;

    /**
     * 获取指定key的set类型的集合数据
     * @param key
     * @return
     * @throws Exception
     */
    Set<Object> sMembers(String key) throws Exception;

    /**
     * 向redis中指定key的set中添加数据
     * @param key
     * @param member
     * @return
     * @throws Exception
     */
    Long sAdd(String key, String member) throws Exception;


    /**
     * 向redis中指定key的set中添加数据集合
     * @param key
     * @param members
     * @return
     * @throws Exception
     */
    Long sAdd(String key, String... members) throws Exception;

    /**
     * 从redis中移除指定key对应的set中的某个值
     * @param key
     * @param member
     * @return
     * @throws Exception
     */

    Long sRemove(String key, String member) throws Exception;

    /**
     * 向redis中的hash类型数据中添加指定的内容
     * @param key
     * @param field
     * @param value
     * @return
     * @throws Exception
     */
    boolean hSet(String key, String field, String value) throws Exception;

    /**
     * 从hash中获取指定属性的值
     * @param key
     * @param field
     * @return
     * @throws Exception
     */
    String hGet(String key, String field) throws Exception;

    /**
     * 获取指定hash中的所有的数据
     * @param key
     * @return
     * @throws Exception
     */
    Map<Object, Object> hGetAll(String key) throws Exception;

    /**
     * 批量向hash中添加数据
     * @param key
     * @param values
     * @return
     * @throws Exception
     */
    boolean hMSet(String key, Map<Object, Object> values) throws Exception;

    /**
     * 查看符合表达式的key的集合
     * @param partten
     * @return
     * @throws Exception
     */
    Set<String> findKeyByPartten(String partten) throws Exception;

    /**
     * 自增指定步长的数据并返回
     * @param key
     * @param delta
     * @return
     * @throws Exception
     */
    Long getAutoIncrementId(String key,long delta) throws Exception;


    /**
     * 从hash中的指定属性上面获取一个自增数据
     * @param key
     * @param field
     * @param delta
     * @return
     * @throws Exception
     */
    Long hIncrementId(String key,String field,long delta) throws Exception;

    /**
     * setnx的方式存放值
     * @param key
     * @param value
     * @param expireTime
     * @return
     * @throws Exception
     */
    boolean setNx(String key, String value, long expireTime) throws Exception;


}
