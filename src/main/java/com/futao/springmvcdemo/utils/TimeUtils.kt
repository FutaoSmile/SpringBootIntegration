package com.futao.springmvcdemo.utils

import com.futao.springmvcdemo.model.entity.BaseEntity
import java.sql.Timestamp
import java.util.*

/**
 * @author futao
 * Created on 2018/10/3.
 * 时间工具类
 */
class TimeUtils {

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
}

fun currentTimeStamp(): Timestamp {
    return Timestamp(Date().time)
}