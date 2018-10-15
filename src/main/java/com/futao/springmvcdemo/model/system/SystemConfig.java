package com.futao.springmvcdemo.model.system;

/**
 * @author futao
 * Created on 2018/9/19-14:50.
 */
public final class SystemConfig {
    /**
     * session中存放登陆用户的id的session key
     */
    public static final String LOGIN_USER_SESSION_KEY = "userLoginUserId";
    /**
     * session过期时间,单位：秒
     */
    public static final int SESSION_INVALIDATE_SECOND = 10;
}
