package com.futao.springbootdemo.model.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 系统配置信息
 *
 * @author futao
 * Created on 2018-12-21.
 */
@Component
public class SystemConfig {

    /**
     * 允许的域名
     */
    public static final String[] ALLOW_ORIGINS = new String[]{
            "http://localhost:8080"
    };

    /**
     * 域名允许访问时间，下一次OPTIONS判断时间
     */
    public static final long ORIGIN_MAX_AGE = 60 * 60 * 24;

    /**
     * 是否开启swagger
     */
    @Value("${systemconfig.enableSwagger}")
    private boolean enableSwagger = false;

    /**
     * 是否开启druidServlet
     */
    @Value("${systemconfig.enableDruidServlet}")
    private boolean enableDruidServlet = false;

    /**
     * 通过拦截器打印sql
     */
    public static final boolean PRINT_SQL_BY_INTERCEPTOR = true;

    public static long getOriginMaxAge() {
        return ORIGIN_MAX_AGE;
    }

    public boolean isEnableSwagger() {
        return enableSwagger;
    }


    public boolean isEnableDruidServlet() {
        return enableDruidServlet;
    }

}
