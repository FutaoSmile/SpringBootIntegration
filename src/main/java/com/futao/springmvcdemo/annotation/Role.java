package com.futao.springmvcdemo.annotation;

import com.futao.springmvcdemo.model.enums.User_Role;

import java.lang.annotation.*;

/**
 * @author futao
 * Created on 2018-12-13.
 * 用户权限控制
 */
@Target({
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@LoginUser
public @interface Role {
    /**
     * 要求的用户角色
     *
     * @return
     */
    User_Role[] value() default User_Role.Normal;
}
