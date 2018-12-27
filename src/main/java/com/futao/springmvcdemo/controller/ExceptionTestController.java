package com.futao.springmvcdemo.controller;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.foundation.configuration.HibernateValidatorConfiguration;
import com.futao.springmvcdemo.foundation.configuration.mq.rocket.RocketMqProducerOnOff;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.service.KotlinTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Conditional;
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
@Api("异常示例")
@Conditional(RocketMqProducerOnOff.class)
public class ExceptionTestController {

    /**
     * 业务逻辑异常
     */
    @ApiOperation("业务逻辑异常")
    @GetMapping(path = "logicException")
    public void logicException() {
        throw LogicException.le(ErrorMessage.LOGIC_EXCEPTION);
    }

    /**
     * 系统未捕获异常
     */
    @ApiOperation("系统未捕获异常")
    @GetMapping(path = "systemException")
    public void systemException() {
        throw new NullPointerException("空指针了，哥门!!!");
    }


    @Resource
    private KotlinTestService service;

    /**
     * Service层异常
     */
    @ApiOperation("Service层异常")
    @GetMapping(path = "serviceException")
    public void serviceException() {
        service.exception();
    }


    /**
     * 验证框架测试
     *
     * @param param
     */
    @ApiOperation("Hibernate Validator 框架校验异常")
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
