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
@ConfigurationProperties(prefix = "systemconfig")
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
     * Service事物超时时间
     */
    public static final int SERVICE_TRANSACTION_TIMEOUT_SECOND = 2;

    /**
     * 通过拦截器打印sql
     */
    public static final boolean PRINT_SQL_BY_INTERCEPTOR = true;


    /**
     * mybatis二级缓存redis中过期时间
     */
    public static final long MYBATIS_CACHE_REDIS_SECOND = 60 * 5;

    /**
     * 是否开启swagger
     */
    private boolean enableSwagger = false;

    /**
     * 是否开启druidServlet
     */
    private boolean enableDruidServlet = false;

    /**
     * redis缓存过期时间
     */
    private long redisCacheSecond = 60 * 5;

    /**
     * Session过期时间
     */
    private int sessionInvalidateSecond = 60 * 5;

    /**
     * 注册邮件验证码过期时间
     */
    private int registerMailCodeExpireSecond = 60 * 5;

    /**
     * 在线人数缩放比例
     */
    private int onlinePeopleQuantityScale = 2;


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

    public long getRedisCacheSecond() {
        return redisCacheSecond;
    }

    public void setRedisCacheSecond(long redisCacheSecond) {
        this.redisCacheSecond = redisCacheSecond;
    }

    public int getSessionInvalidateSecond() {
        return sessionInvalidateSecond;
    }

    public void setSessionInvalidateSecond(int sessionInvalidateSecond) {
        this.sessionInvalidateSecond = sessionInvalidateSecond;
    }

    public int getRegisterMailCodeExpireSecond() {
        return registerMailCodeExpireSecond;
    }

    public void setRegisterMailCodeExpireSecond(int registerMailCodeExpireSecond) {
        this.registerMailCodeExpireSecond = registerMailCodeExpireSecond;
    }

    public int getOnlinePeopleQuantityScale() {
        return onlinePeopleQuantityScale;
    }

    public void setOnlinePeopleQuantityScale(int onlinePeopleQuantityScale) {
        this.onlinePeopleQuantityScale = onlinePeopleQuantityScale;
    }
}
