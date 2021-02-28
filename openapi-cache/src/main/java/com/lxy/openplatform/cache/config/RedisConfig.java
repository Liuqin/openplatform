package com.lxy.openplatform.cache.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * @Author Lvxy
 * @Date 2021/2/26 15:36
 * @Version 1.0
 * @Desc redis配置类 定义自己的redis相关配置
 * 自定义redisTemplate序列化方式
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

    // 重写key的生成策略
    @Override
    public KeyGenerator keyGenerator() {
        // 使用lambda表达式,实现KeyGenerator接口,实现其中Object generate(Object target, Method method, Object... params)方法
        return (target,method,params)->{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(target.getClass().getName());//类名
            stringBuilder.append(method.getName());//方法名

            for (Object param : params) {
                stringBuilder.append(param.toString()); // 参数名
            }

            // test
            System.out.println(stringBuilder.toString());

            return stringBuilder.toString(); //拼接成新的key返回
        };
    }

    // 缓存的管理者 使用的不是jedis,使用的是卷心菜Lettuce
    // Spring Boot2.0之后默认使用Lettuce 作为客户端来连接Redis 服务器
    @Bean
    public CacheManager cacheManager(LettuceConnectionFactory connectionFactory) {
        // 利用Lettuce工厂创建一个writer写入流
        RedisCacheWriter writer = RedisCacheWriter.lockingRedisCacheWriter(connectionFactory);
        // 创建一个默认的配置
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        // new 管理者 通过此来写入缓存
        RedisCacheManager cacheManager = new RedisCacheManager(writer, config);
        return cacheManager;
    }

    /**
    * @Desc 默认帮我们创建的RedisTemplate的key和value的序列化方式是jdk默认的方式,可视化看不懂
    *       所以呢我们有时候手动向redis中添加的数据可能无法被查询解析出来,所以我们需要修改序列化方式
    * @Param connectionFactory
    * @Return
    */
    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory connectionFactory) {

        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();

        redisTemplate.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        //设置key的序列化方式
        redisTemplate.setKeySerializer(stringRedisSerializer);

        //设置hash类型的数据的key的序列化方式
        redisTemplate.setHashKeySerializer(stringRedisSerializer);

        // value序列化选项
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY); // 把什么数据序列化出去(一个对象公有或私有的属性或方法),能够访问到的所有的数据都序列化
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL); //非final类型的数据才会被序列化
        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        //设置value的序列化方式为json
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);

        //设置hash类型的数据的key的序列化方式
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);

        return redisTemplate;
    }


}

