package com.futao.springmvcdemo.controller

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

    @RequestMapping(path = ["/"], method = [(RequestMethod.OPTIONS)])
    open fun option(): HttpStatus {
        return HttpStatus.OK
    }
}