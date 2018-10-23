package com.futao.springmvcdemo.utils

import com.futao.springmvcdemo.foundation.LogicException
import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage
import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.security.MessageDigest
import java.util.*
import kotlin.reflect.KFunction1

/**
 * @author futao
 * Created on 2018/10/15.
 */

const val salt = "white55"
/**
 * 数据库表名前缀
 */
const val tablePrefix = "futao_"
val commonUtilsLogger = LoggerFactory.getLogger("CommonUtilsKt")

/**
 * md5加密方法
 */
fun String.md5(): String? {
    return if (StringUtils.isNotBlank(this)) {
        val md5Digest = MessageDigest.getInstance("MD5")
        String(Hex.encodeHex(md5Digest.digest(this.toByteArray(Charsets.UTF_8))))
    } else {
        null
    }
}

/**
 * 根据getter或者setter方法获取字段小写名称
 */
fun <T, R> KFunction1<T, R>.getFieldName(): String {
    val name = this.name
    return if (name.startsWith("get") || name.startsWith("set")) {
        name.substring(3).toLowerCase()
    } else if (name.startsWith("is")) {
        name.substring(2).toLowerCase()
    } else {
        throw LogicException.le(ErrorMessage.FIELD_NO_GETTER_OR_SETTER)
    }
}

fun uuid(): String {
    return (UUID.randomUUID()).toString().replace("-", "")
}