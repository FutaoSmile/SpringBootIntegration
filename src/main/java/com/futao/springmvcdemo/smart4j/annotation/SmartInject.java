package com.futao.springmvcdemo.smart4j.annotation;

import java.lang.annotation.*;

/**
 * 依赖注入注解
 *
 * @author futao
 * Created on 2019-01-05.
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartInject {

}
