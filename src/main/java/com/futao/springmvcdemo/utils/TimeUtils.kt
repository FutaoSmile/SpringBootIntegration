package com.futao.springmvcdemo.utils

import com.futao.springmvcdemo.model.entity.BaseEntity
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.sql.Timestamp
import java.util.*

/**
 * @author futao
 * Created on 2018/10/3.
 * 时间工具类
 */


/**
 * 获取当前的时间戳
 */


/**
 * 为实体赋值当前时间
 */
fun <T : BaseEntity> T.setCreateAndLastModifyTime() {
    var currentTimeStamp = currentTimeStamp()
    this.createTime = currentTimeStamp
    this.lastModifyTime = currentTimeStamp
}

fun currentTimeStamp(): Timestamp {
    return Timestamp(Date().time)
}

/**
 * 将时间戳字符串转换成DateTime
 */
fun String.toDateTime(): DateTime {
    val timestampFormatter = DateTimeFormat.forPattern(yyyyMMddHHmmss)
    return DateTime.parse(this, timestampFormatter)
}

/**
 * 将DateTime类型转换成时间戳类型
 */
fun DateTime.toTimestamp(): Timestamp {
    return Timestamp(this.millis)
}


const val yyyyMMddHHmmss = "yyyy-MM-dd HH:mm:ss"

const val yyyyMMddhhmmss = "yyyy-MM-dd hh:mm:ss"

const val yyyyMMdd = "yyyy-MM-dd"



