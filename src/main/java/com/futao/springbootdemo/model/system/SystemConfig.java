package com.futao.springbootdemo.model.system;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 系统配置信息
 *
 * @author futao
 * Created on 2018-12-21.
 */
@Component
@ConfigurationProperties("systemconfig")
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
    private boolean enableSwagger = false;

    /**
     * 是否开启druidServlet
     */
    private boolean enableDruidServlet = false;

    public static String[] getAllowOrigins() {
        return ALLOW_ORIGINS;
    }

    public static long getOriginMaxAge() {
        return ORIGIN_MAX_AGE;
    }

    public boolean isEnableSwagger() {
        return enableSwagger;
    }

    public void setEnableSwagger(boolean enableSwagger) {
        this.enableSwagger = enableSwagger;
    }

    public boolean isEnableDruidServlet() {
        return enableDruidServlet;
    }

    public void setEnableDruidServlet(boolean enableDruidServlet) {
        this.enableDruidServlet = enableDruidServlet;
    }
}
