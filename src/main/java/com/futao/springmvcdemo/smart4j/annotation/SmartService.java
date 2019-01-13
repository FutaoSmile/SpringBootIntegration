package com.futao.springmvcdemo.smart4j.annotation;

import java.lang.annotation.*;

/**
 * 服务类注解
 *
 * @author futao
 * Created on 2019-01-05.
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SmartService {
}
