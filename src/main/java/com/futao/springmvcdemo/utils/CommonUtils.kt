package com.futao.springmvcdemo.utils

import org.apache.commons.codec.binary.Hex
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import java.security.MessageDigest

/**
 * @author futao
 * Created on 2018/10/15.
 */

val salt = "white55"
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