package com.futao.springmvcdemo.foundation;

import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.model.system.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 异常处理
 *
 * @author futao
 * Created on 2018/11/6.
 */
@ControllerAdvice
//@ControllerAdvice(basePackages = "com.futao.springmvcdemo.controller")
public class ExceptionWrapper {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionWrapper.class);

    /**
     * 全局异常处理
     *
     * @param e        异常
     * @param request  请求
     * @param response 响应
     * @return 处理后的异常的数据结构
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION);
        //对系统级异常进行日志记录
        LOGGER.error("系统异常:" + e.getMessage(), e);
        return JSONObject.toJSON(result);
    }


    /**
     * 如果是业务逻辑异常，返回具体的错误码与提示信息
     *
     * @param e        异常
     * @param request  请求
     * @param response 响应
     * @return 处理后的异常的数据结构
     */
    @ResponseBody
    @ExceptionHandler(LogicException.class)
    public Object logicExceptionHandler(LogicException e, HttpServletRequest request, HttpServletResponse response) {
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION);
        result.setCode(e.getCode());
        result.setErrorMessage(e.getErrorMsg());
        return JSONObject.toJSON(result);
    }


    /**
     * Validator验证框架抛出的业务逻辑异常
     *
     * @param e        异常
     * @param request  请求
     * @param response 响应
     * @return 处理后的异常的数据结构
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationException(ConstraintViolationException e, HttpServletRequest request, HttpServletResponse response) {
        String message = e.getConstraintViolations().iterator().next().getMessage();
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.SYSTEM_EXCEPTION);
        if (message.contains(ErrorMessage.SEPARATOR)) {
            result.setCode(message.substring(0, 5));
            result.setErrorMessage(message.substring(6));
        } else {
            result.setCode(RestResult.NOT_RE_WRITE_ERROR_MESSAGE);
            result.setErrorMessage(message);
        }
        return JSONObject.toJSON(result);
    }
}
