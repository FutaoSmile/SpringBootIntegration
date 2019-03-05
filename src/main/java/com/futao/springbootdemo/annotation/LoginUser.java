package com.futao.springbootdemo.annotation;

import java.lang.annotation.*;

/**
 * 登陆用户，用户角色
 *
 * @author futao
 * Created on 2018/9/19-14:39.
 */
@Target(value = {
        ElementType.METHOD,
        ElementType.TYPE
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUser {

}
