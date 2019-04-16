package com.futao.springbootdemo.foundation.configuration;

import com.futao.springbootdemo.model.system.RedisKeySet;
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
 *
 * @author futao
 * Created on 2019-03-06.
 */
@SuppressWarnings("unchecked")
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
    public void putObject(Object key, Object value) {
        RedisTemplate redisTemplate = getRedisTemplate();
        ValueOperations opsForValue = redisTemplate.opsForValue();
        String redisKey = RedisKeySet.gen(RedisKeySet.MYBATIS_CACHE + ":" + id, key.toString());
        opsForValue.set(redisKey, value, EXPIRE_TIME_IN_MINUTES, TimeUnit.SECONDS);
        logger.info("\n<<< 结果插入redis缓存\n【key】{}\n【值】:{}", redisKey, value);
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
        String redisKey = RedisKeySet.gen(RedisKeySet.MYBATIS_CACHE + ":" + id, key.toString());
        Object result = opsForValue.get(redisKey);
        logger.info("\n<<< 从redis中查询缓存\n【key】{}\n【结果】:{}", redisKey, result);
        return result;
    }

    /**
     * Remove cached query result from redis
     *
     * @param key
     * @return
     */
    @Override
    public Object removeObject(Object key) {
        RedisTemplate redisTemplate = getRedisTemplate();
        String redisKey = RedisKeySet.gen(RedisKeySet.MYBATIS_CACHE + ":" + id, key.toString());
        logger.info("\n<<< 从redis中移除缓存\n【key】{}", redisKey);
        return redisTemplate.delete(redisKey);
    }

    /**
     * Clears this cache instance
     */
    @Override
    public void clear() {
        RedisTemplate redisTemplate = getRedisTemplate();
//        redisTemplate.execute((RedisCallback) connection -> {
////            connection.flushDb();
////            return null;
////        });
        String redisKey = RedisKeySet.gen(RedisKeySet.MYBATIS_CACHE + ":" + id, "*");

        redisTemplate.delete(redisKey);
        logger.info("\n<<< 清空redis中的前缀为【{}】的缓存", redisKey);
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