package com.futao.springmvcdemo.controller;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.foundation.configuration.HibernateValidatorConfiguration;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.service.KotlinTestService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author futao
 * Created on 2018/9/23-0:28.
 * 统一异常处理测试接口
 */
@RequestMapping(path = "exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Validated
public class ExceptionTestController {

    /**
     * 业务逻辑异常
     */
    @GetMapping(path = "logicException")
    public void logicException() {
        throw LogicException.le(ErrorMessage.LOGIC_EXCEPTION);
    }

    /**
     * 系统异常
     */
    @GetMapping(path = "systemException")
    public void systemException() {
        throw new NullPointerException("空指针了，哥门!!!");
    }


    @Resource
    private KotlinTestService service;

    /**
     * Service层异常
     */
    @GetMapping(path = "serviceException")
    public void serviceException() {
        service.exception();
    }


    /**
     * 验证框架测试
     *
     * @param param
     */
    @PostMapping("validatorTest")
    public void validatorTest(
            @RequestParam("param")
            @Email
            @NotNull
                    String param
    ) {
        User user = new User();
        user.setEmail(param);

        HibernateValidatorConfiguration.validate(user);
    }
}
