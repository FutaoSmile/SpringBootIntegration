package com.futao.springmvcdemo.annotation.interceptor;

import java.lang.annotation.*;

/**
 * @author futao
 * Created on 2018/9/18-14:46.
 * 需要验证签名的注解
 */
@Target(value = {
        ElementType.TYPE,
        ElementType.METHOD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Sign {
}
