package com.futao.springmvcdemo.foundation.configuration

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.serializer.SerializerFeature
import com.futao.springmvcdemo.model.system.Constant
import org.springframework.data.redis.serializer.RedisSerializer
import java.nio.charset.Charset

/**
 *  自定义redis中数据的序列化与反序列化
 *
 * @author futao
 * Created on 2018/10/17.
 */
class FastJsonRedisSerializer<T>(p: Class<T>) : RedisSerializer<T> {

    private val clazz: Class<T>? = null

    /**
     * Serialize the given object to binary data.
     *
     * @param t object to serialize. Can be null.
     * @return the equivalent binary data. Can be null.
     */
    override fun serialize(t: T?): ByteArray? {
        return if (t == null) {
            null
        } else {
            JSON.toJSONString(t, SerializerFeature.WriteClassName).toByteArray(Charset.forName(Constant.UTF8_ENCODE))
        }
    }

    /**
     * Deserialize an object from the given binary data.
     *
     * @param bytes object binary representation. Can be null.
     * @return the equivalent object instance. Can be null.
     */
    override fun deserialize(bytes: ByteArray?): T? {
        return if (bytes == null || bytes.isEmpty()) {
            null
        } else {
            val string = String(bytes, Charset.forName(Constant.UTF8_ENCODE))
            JSON.parseObject(string, clazz) as T
        }
    }
}