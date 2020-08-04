package com.longfei.util;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class RedisUtil {
    @Test
    public void testConnectionRedisSimple() {
        Jedis jedis = new Jedis("106.54.242.90", 6379);
        jedis.set("test","1111111111");
        String result = jedis.get("test");
        System.out.println(result);
    }

}
