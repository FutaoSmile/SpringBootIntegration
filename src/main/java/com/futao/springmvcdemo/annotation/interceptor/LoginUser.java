package com.futao.springmvcdemo.annotation.interceptor;

import com.futao.springmvcdemo.model.enums.Role;

import java.lang.annotation.*;

/**
 * @author futao
 * Created on 2018/9/19-14:39.
 * 登陆用户，用户角色
 */
@Target(value = {
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {
    /**
     * 要求的用户角色
     *
     * @return
     */
    Role role() default Role.Normal;
}
