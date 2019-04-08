package com.futao.springbootdemo.foundation;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.model.system.RestResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.TransactionTimedOutException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

/**
 * 异常处理
 *
 * @author futao
 * Created on 2018/11/6.
 * //@ControllerAdvice(basePackages = "com.futao.springbootdemo.controller")
 */
@RestControllerAdvice
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
    @ExceptionHandler(value = {Exception.class, ApplicationException.class})
    public Object exceptionHandler(Exception e, HttpServletRequest request, HttpServletResponse response) {
        printExceptionLog(e);
        //系统级异常，错误码固定为-1，提示语固定为系统繁忙，请稍后再试
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.ApplicationErrorMessage.SYSTEM_EXCEPTION);
        //对系统级异常进行日志记录
        LOGGER.error("系统异常:" + e.getMessage(), e);
        return result;
    }

    /**
     * 如果是业务逻辑异常，返回具体的错误码与提示信息
     *
     * @param e        异常
     * @param request  请求
     * @param response 响应
     * @return 处理后的异常的数据结构
     */
    @ExceptionHandler(LogicException.class)
    public Object logicExceptionHandler(LogicException e, HttpServletRequest request, HttpServletResponse response) {
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.ApplicationErrorMessage.SYSTEM_EXCEPTION);
        result.setCode(e.getCode());
        result.setErrorMessage(e.getErrorMsg());
        return result;
    }


    /**
     * Validator验证框架抛出的业务逻辑异常
     *
     * @param e        异常
     * @param request  请求
     * @param response 响应
     * @return 处理后的异常的数据结构
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object constraintViolationException(ConstraintViolationException e, HttpServletRequest request, HttpServletResponse response) {
        String message = e.getConstraintViolations().iterator().next().getMessage();
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.ApplicationErrorMessage.SYSTEM_EXCEPTION);
        if (message.contains(ErrorMessage.SEPARATOR)) {
            result.setCode(message.substring(0, 5));
            result.setErrorMessage(message.substring(6));
        } else {
            result.setCode(RestResult.NOT_RE_WRITE_ERROR_MESSAGE);
            result.setErrorMessage(message);
        }
        return result;
    }


    /**
     * 请求超时异常
     *
     * @param e 异常信息
     * @return
     */
    @ExceptionHandler(TransactionTimedOutException.class)
    public Object transactionTimedOutException(TransactionTimedOutException e) {
        printExceptionLog(e);
        return new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.ApplicationErrorMessage.TRANSACTION_TIME_OUT);
    }


    /**
     * 限流Sentinel异常
     *
     * @param e 异常
     * @return
     */
    @ExceptionHandler({BlockException.class, FlowException.class})
    public Object blockException(BlockException e) {
        printExceptionLog(e);
        return new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.LogicErrorMessage.VISIT_TOO_FREQUENTLY);
    }


    /**
     * 处理400,404,405,500等问题
     * 详情{@link org.springframework.web.servlet.DispatcherServlet#noHandlerFound}
     *
     * @param e        异常信息
     * @param response 响应
     * @return
     */
    @ExceptionHandler(ServletException.class)
    public Object httpException(ServletException e, HttpServletResponse response) {
        printExceptionLog(e);
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.LogicErrorMessage.VISIT_TOO_FREQUENTLY);

        if (e instanceof HttpRequestMethodNotSupportedException) {
            result.setCode(String.valueOf(HttpServletResponse.SC_METHOD_NOT_ALLOWED));
            result.setErrorMessage(String.format(ErrorMessage.ApplicationErrorMessage.METHOD_NOT_ALLOWED, ((HttpRequestMethodNotSupportedException) e).getMethod()));
            response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        } else if (e instanceof MissingServletRequestParameterException) {
            result.setCode(String.valueOf(HttpServletResponse.SC_BAD_REQUEST));
            result.setErrorMessage(String.format(ErrorMessage.ApplicationErrorMessage.BAD_REQUEST, ((MissingServletRequestParameterException) e).getParameterName()));
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        } else if (e instanceof NoHandlerFoundException) {
            result.setCode(String.valueOf(HttpServletResponse.SC_NOT_FOUND));
            //url目前获取不到，都是/error
            //result.setErrorMessage(String.format(ErrorMessage.ApplicationErrorMessage.NOT_FOUND, ((NoHandlerFoundException) e).getRequestURL()));
            result.setErrorMessage(ErrorMessage.ApplicationErrorMessage.NOT_FOUND);
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            result.setCode(String.valueOf(HttpServletResponse.SC_INTERNAL_SERVER_ERROR));
            result.setErrorMessage(ErrorMessage.ApplicationErrorMessage.SERVER_ERROR);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }


    @ExceptionHandler(AuthenticationException.class)
    public Object shiroException(Exception e) {
        RestResult result = new RestResult(false, RestResult.SYSTEM_ERROR_CODE, e.getMessage(), ErrorMessage.ApplicationErrorMessage.SYSTEM_EXCEPTION);
        if (e instanceof UnknownAccountException) {
            result.setCode("");
            result.setData(e.getMessage());
            result.setErrorMessage("用户名错误");
        }
        if (e instanceof IncorrectCredentialsException) {
            result.setCode("");
            result.setData(e.getMessage());
            result.setErrorMessage("密码错误");
        }
        return result;
    }

    /**
     * 记录异常日志
     *
     * @param e 异常
     */
    private static void printExceptionLog(Exception e) {
        LOGGER.error(e.getMessage(), e);
    }

}
