package com.longfei.test;

import com.longfei.service.RedisService;
import com.longfei.service.impl.JedisClientSingle;
import com.longfei.service.impl.RedisServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRedisTest {
    private JedisClientSingle jedisClientSingle;
    private RedisServiceImpl redisService;
    @Before
    public void testSpringRedisBefore() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        jedisClientSingle = (JedisClientSingle) ctx.getBean(JedisClientSingle.class);
        redisService= ctx.getBean(RedisServiceImpl.class);
    }

    @Test
    public void testSpringRedis() {
        String resutlt1 = jedisClientSingle.get("test");
        String resutlt2=redisService.getString("test");
        System.out.println(resutlt1+"===="+resutlt2);
    }
}
