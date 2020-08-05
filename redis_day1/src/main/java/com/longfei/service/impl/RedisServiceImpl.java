package com.longfei.service.impl;

import com.longfei.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    RedisTemplate<String,String> redisTemplate;

    public String getString(String key) {
        ValueOperations<String,String> string=redisTemplate.opsForValue();
        if (redisTemplate.hasKey(key)) {
            System.out.println("在Redis中取出并返回");
            return string.get(key);
        }else {
            String result="RedisTemplate的Value值";

            string.set(key,result);
            System.out.println("在数据库中取出并返回");
            return result;
        }
    }

    public RedisTemplate<String, String> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}
