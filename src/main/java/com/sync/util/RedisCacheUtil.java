package com.sync.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisCacheUtil
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/310:11
 * @Version 1.0.0
 */
@Component
public class RedisCacheUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 存储字符串
     * @param key string类型的key
     * @param value String类型的value
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储对象
     * @param key String类型的key
     * @param value Object类型的value
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 存储对象
     * @param key String类型的key
     * @param value Object类型的value
     */
    public void set(String key, Object value,Long timeOut) {
        redisTemplate.opsForValue().set(key, value,timeOut, TimeUnit.SECONDS);
    }

    /**
     * 根据key获取字符串数据
     * @param key
     * @return
     */
    public String getValue(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

//    public Object getValue(String key) {
//        return redisTemplate.opsForValue().get(key);
//    }
    /**
     * 根据key获取对象
     * @param key
     * @return
     */
    public Object getValueOfObject(String key) {
//        System.out.println(redisTemplate.opsForValue());
        return redisTemplate.opsForValue().get(key);

    }
    /**
     * 根据key删除缓存信息
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    /**
     * 查询key是否存在
     * @param key
     * @return
     */
    @SuppressWarnings("unchecked")
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}