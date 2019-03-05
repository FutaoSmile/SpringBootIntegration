package com.futao.springbootdemo.smart4j.annotation;

import java.lang.annotation.*;

/**
 * 标记某个类是一个控制器
 *
 * @author futao
 * Created on 2019-01-05.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartController {
}
