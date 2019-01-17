package com.futao.springmvcdemo.model.system;

/**
 * @author futao
 * Created on 2018-12-21.
 * 系统配置信息
 */
public class SystemConfiguration {

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

}
