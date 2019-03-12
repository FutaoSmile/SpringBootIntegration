package com.futao.springbootdemo.foundation.configuration;

import com.futao.springbootdemo.model.system.SystemConfig;
import com.futao.springbootdemo.utils.SpringUtils;
import org.apache.ibatis.cache.Cache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用redis作为mybatis的二级缓存
 * 已实现，但是目前存在的问题是，insert和update会调用#flushDB方法，导致redis中的数据被清空，导致其他功能不正常（目前是springsession中存储的session信息都被清空了）
 * 所以这个方法还是不建议使用
 *
 * @author futao
 * Created on 2019-03-06.
 */
public class MybatisCacheRedis implements Cache {

    private static final Logger logger = LoggerFactory.getLogger(MybatisCacheRedis.class);

    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    /**
     * cache instance id
     */
    private final String id;
    private RedisTemplate redisTemplate;
    /**
     * redis过期时间
     */
    private static final long EXPIRE_TIME_IN_MINUTES = SystemConfig.MYBATIS_CACHE_REDIS_SECOND;

    public MybatisCacheRedis(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    /**
     * Put query result to redis
     *
     * @param key
     * @param value
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        opsForValue.set(key.toString(), value, EXPIRE_TIME_IN_MINUTES, TimeUnit.SECONDS);
        logger.info("\n<<< Put query result to redis, 【key】:{}【value】:{}", key.toString(), value);
    }

    /**
     * Get cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object getObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        Object result = opsForValue.get(key.toString());
        logger.info("\n<<< Get cached query result from redis, 【key】:{}\n【value】:{}", key.toString(), result);
        return result;
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.delete(key.toString());
        logger.info("\n<<< Remove cached query result from redis, 【key】:{}", key.toString());
        return null;
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
        redisTemplate.execute((RedisCallback) connection -> {
            connection.flushDb();
            return null;
        });
        logger.info("\n<<< Clear all the cached query result from redis");
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }

    private RedisTemplate getRedisTemplate() {
        if (redisTemplate == null) {
            redisTemplate = SpringUtils.getBean("redisTemplate");
        }
        return redisTemplate;
    }
}