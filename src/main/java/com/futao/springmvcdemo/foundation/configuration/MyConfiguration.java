package com.futao.springmvcdemo.foundation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * 通用bean注册管理中心
 *
 * @author futao
 * Created on 2019-01-15.
 */
@org.springframework.context.annotation.Configuration
public class MyConfiguration {

    /**
     * 国际化，设置默认的语言为中文
     * 将用户的区域信息存在session
     * 也可以存在cookie,由客户端保存   {@link org.springframework.web.servlet.i18n.CookieLocaleResolver}
     *
     * @return
     */
    @Bean
    public LocaleResolver localeResolver() {
        CookieLocaleResolver slr = new CookieLocaleResolver();
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);
        return slr;
    }

    /**
     * 国际化，设置url识别参数
     *
     * @return
     */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
}
