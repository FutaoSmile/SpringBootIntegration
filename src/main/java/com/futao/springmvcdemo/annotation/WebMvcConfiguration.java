package com.futao.springmvcdemo.annotation;

import com.futao.springmvcdemo.annotation.impl.LoginUserInterceptor;
import com.futao.springmvcdemo.annotation.impl.RequestLogInterceptor;
import com.futao.springmvcdemo.annotation.impl.SignInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2018/9/18-15:15.
 */
@SpringBootConfiguration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Resource
    private SignInterceptor signInterceptor;
    @Resource
    private LoginUserInterceptor loginUserInterceptor;
    @Resource
    private RequestLogInterceptor requestLogInterceptor;

    /**
     * addInterceptor()的顺序需要严格按照程序的执行的顺序
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(requestLogInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginUserInterceptor).addPathPatterns("/**");
        //  "/**"和"/*"是有区别的
        registry.addInterceptor(signInterceptor).addPathPatterns("/**");
    }
}
