package com.longfei.test;

import com.longfei.service.RedisService;
import com.longfei.service.impl.JedisClientSingle;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringRedisTest {
    private JedisClientSingle jedisClientSingle;

    @Before
    public void testSpringRedisBefore() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-redis.xml");
        jedisClientSingle = (JedisClientSingle) ctx.getBean(JedisClientSingle.class);
    }

    @Test
    public void testSpringRedis() {
        String resutlt = jedisClientSingle.get("test");
        System.out.println(resutlt);
    }
}
