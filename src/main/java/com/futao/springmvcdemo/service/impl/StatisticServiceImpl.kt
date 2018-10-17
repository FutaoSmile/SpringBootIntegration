package com.futao.springmvcdemo.service.impl

import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage
import com.futao.springmvcdemo.model.system.ErrorMessageFields
import com.futao.springmvcdemo.service.StatisticService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import java.util.*

/**
 * @author futao
 * Created on 2018/10/16.
 */
@Service
open class StatisticServiceImpl : StatisticService {

    @Cacheable(value = ["errorMessages"])
    override fun getErrorMessages(): ArrayList<ErrorMessageFields> {
        val errorMessageClass = ErrorMessage::class.java
        val list = ArrayList<ErrorMessageFields>()
        for (field in errorMessageClass.fields) {
            list.add(ErrorMessageFields(field.name, field.get(ErrorMessage::class.java).toString()))
        }
        return list
    }
}