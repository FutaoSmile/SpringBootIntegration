package com.futao.springbootdemo.annotation;

import com.futao.springbootdemo.model.enums.UserRoleEnum;

import java.lang.annotation.*;

/**
 * 简单用户权限控制
 *
 * @author futao
 * Created on 2018-12-13.
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
    UserRoleEnum[] value() default UserRoleEnum.NORMAL;
}
