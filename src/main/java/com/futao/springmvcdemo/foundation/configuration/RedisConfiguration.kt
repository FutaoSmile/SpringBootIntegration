package com.futao.springmvcdemo.foundation.configuration

import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CachingConfigurerSupport
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.interceptor.KeyGenerator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager
import org.springframework.data.redis.cache.RedisCacheWriter
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration
import javax.annotation.Resource

/**
 * redis配置类
 *
 * @author futao
 * Created on 2018/10/16.
 *
 * redisTemplate.opsForValue();//操作字符串
 * redisTemplate.opsForHash();//操作hash
 * redisTemplate.opsForList();//操作list
 * redisTemplate.opsForSet();//操作set
 * redisTemplate.opsForZSet();//操作有序set
 *
 */
@Configuration
@EnableCaching
open class RedisConfiguration : CachingConfigurerSupport() {


    /**
     * 自定义redis key的生成规则
     */
    @Bean
    override fun keyGenerator(): KeyGenerator {
        return KeyGenerator { target, method, params ->
            val builder = StringBuilder()
            builder.append("${target.javaClass.simpleName}-")
                    .append("${method.name}-")
            for (param in params) {
                builder.append("$param-")
            }
            builder.toString().toLowerCase()
        }
    }

    /**
     * 自定义序列化
     * 这里的FastJsonRedisSerializer引用的自己定义的
     */
    @Bean
    open fun redisTemplate(factory: RedisConnectionFactory): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()
        val fastJsonRedisSerializer = FastJsonRedisSerializer(Any::class.java)
        val stringRedisSerializer = StringRedisSerializer()
        return redisTemplate.apply {
            defaultSerializer = fastJsonRedisSerializer
            keySerializer = stringRedisSerializer
            hashKeySerializer = stringRedisSerializer
            valueSerializer = fastJsonRedisSerializer
            hashValueSerializer = fastJsonRedisSerializer
            connectionFactory = factory
        }
    }


    @Resource
    lateinit var factory: RedisConnectionFactory

    override fun cacheManager(): CacheManager {
        val configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(3))
                .disableCachingNullValues()

        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(factory))
                .cacheDefaults(configuration)
                .build()
    }
}