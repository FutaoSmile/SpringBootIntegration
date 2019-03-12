package com.futao.springbootdemo.controller;

import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.foundation.LogicException;
import com.futao.springbootdemo.foundation.configuration.HibernateValidatorConfiguration;
import com.futao.springbootdemo.foundation.configuration.mq.rocket.RocketMqProducerOnOff;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.KotlinTestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

/**
 * 统一异常处理测试接口
 *
 * @author futao
 * Created on 2018/9/23-0:28.
 */
@RequestMapping(path = "exception", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Validated
@Api("异常示例")
@Conditional(RocketMqProducerOnOff.class)
public class ExceptionTestController {

    @Resource
    private KotlinTestService service;

    /**
     * 业务逻辑异常
     */
    @ApiOperation("业务逻辑异常")
    @GetMapping(path = "logicException")
    public void logicException() {
        throw LogicException.le(ErrorMessage.LogicErrorMessage.LOGIC_EXCEPTION);
    }

    /**
     * 系统未捕获异常
     */
    @ApiOperation("系统未捕获异常")
    @GetMapping(path = "systemException")
    public void systemException() {
        throw new NullPointerException("空指针了，哥门!!!");
    }


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
            @NotNull
                    int param
    ) {
        User user = new User();
//        user.setRole(param);

        HibernateValidatorConfiguration.validate(user);
    }

    /**
     * applicationException
     */
    @PostMapping("applicationException")
    @ApiOperation("applicationException测试")
    public void applicationException() {
        throw ApplicationException.ae("ApplicationErrorMessage");
    }
}
