package com.futao.springmvcdemo.controller

import com.futao.springmvcdemo.model.entity.SingleValueResult
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.annotations.ApiIgnore
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * @author futao
 * Created on 2018/11/6.
 */
@RestController
@ApiIgnore
open class OpenController {

    @RequestMapping(path = ["/"], method = [RequestMethod.GET, RequestMethod.OPTIONS])
    open fun option(request: HttpServletRequest, response: HttpServletResponse): SingleValueResult {
        response.sendRedirect("http://47.106.247.59:8888/swagger-ui.html#")
        return SingleValueResult("swagger api地址: http://localhost:8888/swagger-ui.html#!" +
                "druid连接池地址: http://localhost:8888/druid/index.html")
    }
}