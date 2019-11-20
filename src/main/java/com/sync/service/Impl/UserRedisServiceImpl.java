package com.sync.service.Impl;

import com.google.gson.Gson;
import com.sync.entity.User;
import com.sync.service.UserRedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserRedisServiceImpl
 * @Description TODO
 * @Author yankai
 * @Date 2019/10/221:57
 * @Version 1.0.0
 */
@Repository
public class UserRedisServiceImpl implements UserRedisService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Long getIdByName(String name) {
        Gson gson = new Gson();
        User user = null;
        String userJson = redisTemplate.opsForValue().get(name);
        if(StringUtils.isNotEmpty(userJson)){
            user =  gson.fromJson(userJson, User.class);
        }
        if(user != null || !user.equals(null)){
            return user.getUserId();
        }
        return null;
    }
}
