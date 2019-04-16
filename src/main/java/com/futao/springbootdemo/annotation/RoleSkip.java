package com.futao.springbootdemo.annotation;

import java.lang.annotation.*;

/**
 * 忽略
 *
 * @author futao
 * Created on 2019-04-11.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface RoleSkip {
}
