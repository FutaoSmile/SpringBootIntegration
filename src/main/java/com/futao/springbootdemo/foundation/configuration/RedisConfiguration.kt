package com.futao.springbootdemo.foundation.configuration

import com.futao.springbootdemo.model.system.SystemConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
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
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer
import redis.clients.jedis.JedisPoolConfig
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

    @Autowired
    private lateinit var redisProperties: RedisProperties

    @Resource
    lateinit var systemConfig: SystemConfig

    /**
     * 自定义redis key的生成规则
     */
    @Bean
    override fun keyGenerator(): KeyGenerator {
        return KeyGenerator { target, method, params ->
            val builder = StringBuilder()
            builder.append("${target.javaClass.simpleName}:")
                    .append("${method.name}:")
            for (param in params) {
                builder.append("$param:")
            }
            builder.toString().substring(0, builder.length - 1).toLowerCase()
        }
    }

    /**
     * 自定义序列化
     * 这里的FastJsonRedisSerializer引用的自己定义的
     * 不自定义的话redisTemplate会乱码
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


    /**
     * 连接池
     */
    @Bean
    open fun connectionFactory(): RedisConnectionFactory {
        val poolConfig = JedisPoolConfig()
        poolConfig.maxTotal = redisProperties.jedis.pool.maxActive
        poolConfig.maxIdle = redisProperties.jedis.pool.maxIdle
        poolConfig.maxWaitMillis = redisProperties.jedis.pool.maxWait.toMillis()
        poolConfig.testOnBorrow = true
        poolConfig.testOnReturn = false
        poolConfig.testWhileIdle = true
        val jedisClientConfiguration: JedisClientConfiguration?

        jedisClientConfiguration = if (redisProperties.isSsl) {
            JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).and().readTimeout(redisProperties.timeout).useSsl()
                    .build()
        } else {
            JedisClientConfiguration.builder().usePooling().poolConfig(poolConfig).and().readTimeout(redisProperties.timeout).build()
        }
        val redisStandaloneConfiguration = RedisStandaloneConfiguration()

        redisStandaloneConfiguration.database = redisProperties.database
        redisStandaloneConfiguration.port = redisProperties.port
        redisStandaloneConfiguration.password = RedisPassword.of(redisProperties.password)
        redisStandaloneConfiguration.hostName = redisProperties.host
        return JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration!!)
    }


    /**
     * 缓存管理器
     */
    override fun cacheManager(): CacheManager {
        val configuration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(systemConfig.redisCacheSecond))
                .disableCachingNullValues()

        return RedisCacheManager.builder(RedisCacheWriter.lockingRedisCacheWriter(connectionFactory()))
                .cacheDefaults(configuration)
                .build()
    }
}