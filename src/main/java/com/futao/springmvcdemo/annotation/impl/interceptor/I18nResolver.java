package com.futao.springmvcdemo.annotation.impl.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * 自定义区域信息解析器（默认的区域信息解析器是从request.header中获取Accept-Language: zh,en-US;q=0.9,en;q=0.8,zh-CN;q=0.7）
 *
 * @author futao
 * Created on 2019-01-09.
 */
@Component
public class I18nResolver implements LocaleResolver {
    /**
     * 解析区域信息
     *
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = Locale.getDefault();
        //请求地址中带上该参数，说明需要请求的语言
        String lang = request.getParameter("lang");
        if (!StringUtils.isEmpty(lang)) {
            locale = new Locale("lang.l", "lang.c");
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {

    }
}
