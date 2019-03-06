package com.futao.springbootdemo.foundation;

import com.futao.springbootdemo.model.system.RestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 返回Rest风格的数据
 *
 * @author futao
 * Created on 2018/9/22-20:24.
 */
@ControllerAdvice(basePackages = "com.futao.springbootdemo.controller")
public class RestResultWrapper implements ResponseBodyAdvice<Object> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestResultWrapper.class);

    /**
     * 可指定针对某些返回值的类型才进行rest风格的封装
     *
     * @param returnType    返回值类型
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * 封装正常返回的数据
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        RestResult result = new RestResult(true, RestResult.SUCCESS_CODE, body, null);
//        return JSON.toJSON(result);
        return result;
    }
}
