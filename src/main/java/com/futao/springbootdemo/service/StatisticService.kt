package com.futao.springbootdemo.service

import com.alibaba.fastjson.JSONArray
import com.futao.springbootdemo.model.entity.ApiControllerDescription
import com.futao.springbootdemo.model.system.ErrorMessageFields

interface StatisticService {

    fun enumList(): MutableMap<String, JSONArray>?
    @Throws(IllegalAccessException::class)
    fun getErrorMessages(): List<ErrorMessageFields>

    fun apiList(): ArrayList<ApiControllerDescription>
}
