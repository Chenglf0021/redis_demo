package com.longfei.service.impl;

import com.longfei.service.JedisClient;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * 集群模式下的redis实现
 */
public class JedisClientCluster implements JedisClient {
    private JedisCluster jedisCluster;

    public String get(String key) {
        return null;
    }

    public String set(String key, String value) {
        return null;
    }

    public Long expire(String key, int second) {
        return null;
    }

    public Long del(String key) {
        return null;
    }

    public Boolean exists(String key) {
        return null;
    }

    public Long rpush(String key, String value) {
        return null;
    }

    public String lpop(String key) {
        return null;
    }

    public Long llen(String key) {
        return null;
    }

    public List<String> lrange(String key, int start, int end) {
        return null;
    }
}
