package com.futao.springmvcdemo.smart4j.annotation;

import java.lang.annotation.*;

/**
 * 标记某个方法是Action
 *
 * @author futao
 * Created on 2019-01-05.
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartAction {
    /**
     * 请求类型与路径
     *
     * @return
     */
    String value();
}
