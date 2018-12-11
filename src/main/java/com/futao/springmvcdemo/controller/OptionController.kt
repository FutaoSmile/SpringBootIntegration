package com.futao.springmvcdemo.controller

import com.futao.springmvcdemo.model.entity.SingleValueResult
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

/**
 * @author futao
 * Created on 2018/11/6.
 */
@RestController
open class OpenController {

    @RequestMapping(path = ["/"], method = [RequestMethod.GET, RequestMethod.OPTIONS])
    open fun option(): SingleValueResult {
        return SingleValueResult("swagger api地址: http://localhost:8888/swagger-ui.html#!\n" +
                "druid连接池地址: http://localhost:8888/druid/index.html")
    }
}