package com.futao.springmvcdemo.annotation;

import com.futao.springmvcdemo.foundation.MyImport;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author futao
 * Created on ${date}.
 */
@Target(value = ElementType.TYPE)
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Import(value = MyImport.class)
public @interface EnableEntity {
}
