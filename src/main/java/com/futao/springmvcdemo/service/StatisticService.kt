package com.futao.springmvcdemo.service

import com.alibaba.fastjson.JSONArray
import com.futao.springmvcdemo.model.entity.ApiControllerDescription
import com.futao.springmvcdemo.model.system.ErrorMessageFields

interface StatisticService {

    fun enumList(): MutableMap<String, JSONArray>?
    @Throws(IllegalAccessException::class)
    fun getErrorMessages(): List<ErrorMessageFields>

    fun apiList(): ArrayList<ApiControllerDescription>
}
