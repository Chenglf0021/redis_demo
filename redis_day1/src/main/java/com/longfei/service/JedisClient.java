package com.longfei.service;

import java.util.List;

public interface JedisClient {

    //获取值
    String get(String key);
    //设置值
    String set(String key,String value);
    /*//获取hash类型的值
    String hget(String hkey,String key);
    //设置hash类型的值
    Long hset(String hkey,String key,String value);
    //使某个值自增1
    Long incr(String key);*/
    //设置某个值的有效期，单位是秒
    Long expire(String key,int second);
    //获取某个值的有效期（返回-1为永久有效，返回-2为已经失效，返回其他正整数为剩余有效期毫秒值）
//    Long ttl(String key);
    //删除缓存数据
    Long del(String key);
    //删除hash类型数据linsert
//    Long hdel(String hkey,String key);
    Boolean exists(String key);
    Long rpush(String key, String value);
    String lpop(String key);
    Long llen(String key);

    List<String> lrange(String key, int start, int end);
//    Long lrem(String key, Long count, String value);
}
