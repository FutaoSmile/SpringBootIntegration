package com.futao.springmvcdemo.annotation.interceptor.impl;

import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static com.futao.springmvcdemo.utils.RequestUtils.getCookies;
import static com.futao.springmvcdemo.utils.RequestUtils.getSessionParameters;

/**
 * @author futao
 * Created on 2018/9/20-12:12.
 * 请求controller记录日志，以及接口请求时间记录
 */
@Component
public class RequestLogInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestLogInterceptor.class);
    /**
     * 请求开始的时间
     */
    private long startTime;
    /**
     * 标识一次请求
     */
    private UUID uuid;

    /**
     * 统计接口请求次数请求
     */
    private ConcurrentHashMap<String, AtomicInteger> apiRequestStatistic = new ConcurrentHashMap();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        startTime = DateTime.now().getMillis();
//        uuid = UUID.randomUUID();
//        logger.info("请求(id=" + uuid + ")开始：" + StringUtils.repeat("↓", 10));
        if (handler instanceof HandlerMethod) {
            Method method = ((HandlerMethod) handler).getMethod();
            RestController restController = method.getDeclaringClass().getAnnotation(RestController.class);
            if (ObjectUtils.allNotNull(restController)) {
                StringBuilder sb = new StringBuilder();
                sb.append("\n")
                  .append("请求地址: " + request.getRequestURL())
                  .append("\n")
                  .append("请求sessions: " + getSessionParameters(request.getSession(false)))
                  .append("\n")
                  .append("请求参数：" + queryString(request.getQueryString()))
                  .append("\n")
                  .append("请求cookies: " + getCookies(request.getCookies()));
                logger.info(String.valueOf(sb));
            }
            String methodName = method.getDeclaringClass() + ".< " + method.getName() + " >";
            AtomicInteger atomicInteger = apiRequestStatistic.get(methodName);
            if (atomicInteger == null) {
                apiRequestStatistic.put(methodName, new AtomicInteger(1));
            } else {
                apiRequestStatistic.put(methodName, new AtomicInteger(atomicInteger.incrementAndGet()));
            }
        }
        return true;
    }

    private String queryString(String qStr) throws UnsupportedEncodingException {
        return qStr == null ? "" : URLDecoder.decode(qStr, "UTF-8");
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//        logger.info("请求(id=" + uuid + ")结束：" + StringUtils.repeat("↑", 10) + "本次请求所消耗的时间（毫秒）：" + (DateTime.now().minus(startTime)).getMillis());
    }

    public ConcurrentHashMap<String, AtomicInteger> getApiRequestStatistic() {
        return apiRequestStatistic;
    }
}
