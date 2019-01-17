package com.futao.springmvcdemo.controller;

import java.lang.annotation.*;

/**
 * @author futao
 * Created on 2019-01-14.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface SentinelAnn {
    String resource() default "";
}
