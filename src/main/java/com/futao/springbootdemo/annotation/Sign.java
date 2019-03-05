package com.futao.springbootdemo.annotation;

import java.lang.annotation.*;

/**
 * 需要验证签名的注解
 *
 * @author futao
 * Created on 2018/9/18-14:46.
 */
@Target(value = {
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {
}
