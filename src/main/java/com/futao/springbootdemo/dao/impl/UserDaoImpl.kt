package com.futao.springbootdemo.dao.impl

import com.futao.springbootdemo.model.entity.User
import com.futao.springbootdemo.utils.getFieldName
import org.apache.ibatis.jdbc.SQL
import org.springframework.stereotype.Repository

/**
 * @author futao
 * Created on 2018/10/17.
 */
@Repository
open class UserDaoImpl {
    fun add(name: String): String {
        return SQL()
                .SELECT("*")
                .FROM("")
                .WHERE("${User::getAddress.getFieldName()}=$name")
                .ORDER_BY(User::getCreateTime.getFieldName())
                .toString()
    }
}