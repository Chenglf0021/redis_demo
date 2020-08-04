package com.longfei.service.impl;

import com.longfei.service.JedisClient;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Set;
//单机版
@Service
public class JedisClientSingle implements JedisClient {
    //单机版需要注入在 application-jedis.xml中配置的bean
    private JedisPool jedisPool;

    /**
     * 必须有set方法，否则无法属性注入
     */
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public String get(String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    public String set(String key, String value) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.set(key, value);
        jedis.close();
        return result;
    }

    public Boolean exists(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.exists(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    public String hget(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        String result = jedis.hget(hkey, key);
        jedis.close();
        return result;
    }

    public Long hset(String hkey, String key, String value) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    public Long incr(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    public Long expire(String key, int second) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    public Long ttl(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    public Long del(String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    public Long hdel(String hkey, String key) {
        Jedis jedis = jedisPool.getResource();
        Long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }

    /**************************** redis 列表List start***************************/

    /**
     * 将一个值插入到列表头部，value可以重复，返回列表的长度
     * @param key
     * @param value String
     * @return 返回List的长度
     */
    public  Long lpush(String key, String value){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpush(key, value);
        jedis.close();
        return length;
    }

    /**
     * 将多个值插入到列表头部，value可以重复
     * @param key
     * @param values String[]
     * @return 返回List的数量size
     */
    public  Long lpush(String key, String[] values){
        Jedis jedis = jedisPool.getResource();
        Long size = jedis.lpush(key, values);
        jedis.close();
        //System.out.println(result);
        return size;
    }

    /**
     * 获取List列表
     * @param key
     * @param start Long，开始索引
     * @param end Long， 结束索引
     * @return List<String>
     */
    public  List<String> lrange(String key, int start, int end){
        Jedis jedis = jedisPool.getResource();
        List<String> list = jedis.lrange(key, start, end);
        jedis.close();
        return list;
    }

    /**
     * 通过索引获取列表中的元素
     * @param key
     * @param index，索引，0表示最新的一个元素
     * @return String
     */
    public  String lindex(String key, Long index){
        Jedis jedis = jedisPool.getResource();
        String str = jedis.lindex(key, index);
        jedis.close();
        return str;
    }

    /**
     * 获取列表长度，key为空时返回0
     * @param key
     * @return Long
     */
    public  Long llen(String key){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.llen(key);
        jedis.close();
        return length;
    }

    /**
     * 在列表的元素前或者后插入元素，返回List的长度
     * @param key
     * @param where LIST_POSITION
     * @param pivot 以该元素作为参照物，是在它之前，还是之后（pivot：枢轴;中心点，中枢;[物]支点，支枢;[体]回转运动。）
     * @param value
     * @return Long
     */
/*    public  Long linsert(String key, LIST_POSITION where, String pivot, String value){
        Jedis jedis = jedisPool.getResource();
       // Long length = 0;//jedis.linsert(key, where, pivot, value);
        jedis.close();
        return null;
    }*/

    /**
     * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
     * @param key
     * @param value String
     * @return Long
     */
    public  Long lpushx(String key, String value){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpushx(key, value);
        jedis.close();
        return length;
    }

    /**
     * 将一个或多个值插入到已存在的列表头部，当成功时，返回List的长度；当不成功（即key不存在时，返回0）
     * @param key
     * @param values String[]
     * @return Long
     */
    public  Long lpushx(String key, String[] values){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lpushx(key, values);
        jedis.close();
        return length;
    }

    /**
     * 移除列表元素，返回移除的元素数量
     * @param key
     * @param count，标识，表示动作或者查找方向
     * <li>当count=0时，移除所有匹配的元素；</li>
     * <li>当count为负数时，移除方向是从尾到头；</li>
     * <li>当count为正数时，移除方向是从头到尾；</li>
     * @param value 匹配的元素
     * @return Long
     */
    public  Long lrem(String key, Long count, String value){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.lrem(key, count, value);
        jedis.close();
        return length;
    }

    /**
     * 通过索引设置列表元素的值，当超出索引时会抛错。成功设置返回true
     * @param key
     * @param index 索引
     * @param value
     * @return Boolean
     */
    public  Boolean lset(String key, Long index, String value){
        Jedis jedis = jedisPool.getResource();
        String statusCode = jedis.lset(key, index, value);
        jedis.close();
        if("OK".equalsIgnoreCase(statusCode)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * @param key
     * @param start
     * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     * <li>如果start大于end，则返回一个空的列表，即列表被清空</li>
     * @param end
     * <li>可以为负数（-1是列表的最后一个元素，-2是列表倒数第二的元素。）</li>
     * <li>可以超出索引，不影响结果</li>
     * @return Boolean
     */
    public  Boolean ltrim(String key, Long start, Long end){
        Jedis jedis = jedisPool.getResource();
        String statusCode = jedis.ltrim(key, start, end);
        jedis.close();
        if("OK".equalsIgnoreCase(statusCode)){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 移出并获取列表的第一个元素，当列表不存在或者为空时，返回Null
     * @param key
     * @return String
     */
    public  String lpop(String key){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.lpop(key);
        jedis.close();
        return value;
    }

    /**
     * 移除并获取列表最后一个元素，当列表不存在或者为空时，返回Null
     * @param key
     * @return String
     */
    public  String rpop(String key){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.rpop(key);
        jedis.close();
        return value;
    }

    /**
     * 在列表中的尾部添加一个个值，返回列表的长度
     * @param key
     * @param value
     * @return Long
     */
    public  Long rpush(String key, String value){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpush(key, value);
        jedis.close();
        return length;
    }

    /**
     * 在列表中的尾部添加多个值，返回列表的长度
     * @param key
     * @param values
     * @return Long
     */
    public  Long rpush(String key, String[] values){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpush(key, values);
        jedis.close();
        return length;
    }

    /**
     * 仅当列表存在时，才会向列表中的尾部添加一个值，返回列表的长度
     * @param key
     * @param value
     * @return Long
     */
    public  Long rpushx(String key, String value){
        Jedis jedis = jedisPool.getResource();
        Long length = jedis.rpushx(key, value);
        jedis.close();
        return length;
    }

    /**
     * 移除列表的最后一个元素，并将该元素添加到另一个列表并返回
     * @param sourceKey 源列表的key，当源key不存在时，结果返回Null
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @return String
     */
    public  String rpopLpush(String sourceKey, String targetKey){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.rpoplpush(sourceKey, targetKey);
        jedis.close();
        return value;
    }

    /**
     * 移出并获取列表的【第一个元素】， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout 单位为秒
     * @param keys
     * <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     * <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     * <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return List<String>
     */
    public  List<String> blpop(int timeout, String... keys){
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.blpop(timeout, keys);
        jedis.close();
        return values;
    }

    /**
     * 移出并获取列表的【最后一个元素】， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout 单位为秒
     * @param keys
     * <li>当有多个key时，只要某个key值的列表有内容，即马上返回，不再阻塞。</li>
     * <li>当所有key都没有内容或不存在时，则会阻塞，直到有值返回或者超时。</li>
     * <li>当超期时间到达时，keys列表仍然没有内容，则返回Null</li>
     * @return List<String>
     */
    public  List<String> brpop(int timeout, String... keys){
        Jedis jedis = jedisPool.getResource();
        List<String> values = jedis.brpop(timeout, keys);
        jedis.close();
        return values;
    }

    /**
     * 从列表中弹出列表最后一个值，将弹出的元素插入到另外一个列表中并返回它；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param sourceKey 源列表的key，当源key不存在时，则会进行阻塞
     * @param targetKey 目标列表的key，当目标key不存在时，会自动创建新的
     * @param timeout 单位为秒
     * @return String
     */
    public  String brpopLpush(String sourceKey, String targetKey, int timeout){
        Jedis jedis = jedisPool.getResource();
        String value = jedis.brpoplpush(sourceKey, targetKey, timeout);
        jedis.close();
        return value;
    }

    /**************************** redis 列表List end***************************/

    /**************************** redis 集合Set start***************************/
    /**Redis的Set是string类型的无序集合。集合成员是唯一的，这就意味着集合中不能出现重复的数据。**/


    /**
     * 向集合添加一个或多个成员，返回添加成功的数量
     * @param key
     * @param members
     * @return Long
     */
    public  Long sadd(String key, String... members){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sadd(key, members);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 获取集合的成员数
     * @param key
     * @return
     */
    public  Long scard(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.scard(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回集合中的所有成员
     * @param key
     * @return Set<String>
     */
    public Set<String> smembers(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.smembers(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 判断 member 元素是否是集合 key 的成员，在集合中返回True
     * @param key
     * @param member
     * @return Boolean
     */
    public  Boolean sIsMember(String key, String member){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sismember(key, member);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的差集（获取第一个key中与其它key不相同的值，当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return Set<String>
     */
    public  Set<String> sdiff(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sdiff(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的差集并存储在 targetKey中，类似sdiff，只是该方法把返回的差集保存到targetKey中
     * <li>当有差集时，返回true</li>
     * <li>当没有差集时，返回false</li>
     * @param targetKey
     * @param keys
     * @return
     */
    public  Boolean sdiffStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.sdiffstore(targetKey, keys);
            if(1L == statusCode){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 返回给定所有集合的交集（获取第一个key中与其它key相同的值，要求所有key都要有相同的值，如果没有相同，返回Null。当只有一个key时，就返回这个key的所有值）
     * @param keys
     * @return Set<String>
     */
    public  Set<String> sinter(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sinter(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回给定所有集合的交集并存储在 targetKey中，类似sinter
     * @param targetKey
     * @param keys
     * @return Boolean
     */
    public  Boolean sinterStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long statusCode = jedis.sinterstore(targetKey, keys);
            if(1L == statusCode){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 将 member 元素从 sourceKey 集合移动到 targetKey 集合
     * <li>成功返回true</li>
     * <li>当member不存在于sourceKey时，返回false</li>
     * <li>当sourceKey不存在时，也返回false</li>
     * @param sourceKey
     * @param targetKey
     * @param member
     * @return Boolean
     */
    public  Boolean smove(String sourceKey, String targetKey, String member){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            Long value = jedis.smove(sourceKey, targetKey, member);
            if(value > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 移除并返回集合中的一个随机元素
     * <li>当set为空或者不存在时，返回Null</li>
     * @param key
     * @return String
     */
    public  String spop(String key){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.spop(key);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 返回集合中一个或多个随机数
     * <li>当count大于set的长度时，set所有值返回，不会抛错。</li>
     * <li>当count等于0时，返回[]</li>
     * <li>当count小于0时，也能返回。如-1返回一个，-2返回两个</li>
     * @param key
     * @param count
     * @return List<String>
     */
    public  List<String> srandMember(String key, int count){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.srandmember(key, count);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 移除集合中一个或多个成员
     * @param key
     * @param members
     * @return
     */
    public  Boolean srem(String key, String... members){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //Integer reply, specifically: 1 if the new element was removed
            //0 if the new element was not a member of the set
            Long value = jedis.srem(key, members);
            if(value > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**
     * 返回所有给定集合的并集，相同的只会返回一个
     * @param keys
     * @return
     */
    public  Set<String> sunion(String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.sunion(keys);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    /**
     * 所有给定集合的并集存储在targetKey集合中
     * <li>注：合并时，只会把keys中的集合返回，不包括targetKey本身</li>
     * <li>如果targetKey本身是有值的，合并后原来的值是没有的，因为把keys的集合重新赋值给targetKey</li>
     * <li>要想保留targetKey本身的值，keys要包含原来的targetKey</li>
     * @param targetKey
     * @param keys
     * @return
     */
    public  Boolean sunionStore(String targetKey, String... keys){
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //返回合并后的长度
            Long statusCode = jedis.sunionstore(targetKey, keys);
            if(statusCode > 0){
                return true;
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(jedis != null){
                jedis.close();
            }
        }
        return false;
    }

    /**************************** redis 集合Set end***************************/

}
