package com.longfei.springboot_redis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRedisController {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("hello")
    public String helloRedisInSpringBoot() {
        ValueOperations ops2 = redisTemplate.opsForValue();
        ValueOperations ops1=stringRedisTemplate.opsForValue();
        ops2.set("test","hello springboot_redis");
        return ops2.get("test").toString()+ops1.get("test");

    }
}
