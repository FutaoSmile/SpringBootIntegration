package com.futao.springmvcdemo.service

import com.futao.springmvcdemo.model.system.ErrorMessageFields
import java.util.*

interface StatisticService {
    @Override
    open fun getErrorMessages(): ArrayList<ErrorMessageFields>

}
