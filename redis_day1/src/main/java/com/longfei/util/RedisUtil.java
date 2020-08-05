package com.longfei.util;

import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class RedisUtil {
    /**
     * @简单模式
     */
    @Test
    public void testConnectionRedisSimple() {
        Jedis jedis = new Jedis("106.54.242.90", 6379);
        jedis.set("test", "1111111111");
        String result = jedis.get("test");
        System.out.println(result);
        jedis.close();
    }

    /**
     * @Desc:连接池
     */
    @Test
    public void testConnectRedisByPool() {
        JedisPool jedisPool = new JedisPool("106.54.242.90", 6379);
        Jedis jedis = jedisPool.getResource();
        jedis.get("test");
        jedis.close();
        jedisPool.close();
    }

    /**
     * @Desc:集群
     */
    @Test
    public void testConnectRedisFactory(){
        Set<HostAndPort> hostAndPortSet=new HashSet<HostAndPort>();
        hostAndPortSet.add(new HostAndPort("106.54.242.90",6379));
        hostAndPortSet.add(new HostAndPort("",6379));
        JedisCluster jedisCluster=new JedisCluster(hostAndPortSet);
        String result=jedisCluster.get("test");
        System.out.println(result);
        try {
            jedisCluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
