package com.futao.springmvcdemo.annotation;

import com.futao.springmvcdemo.annotation.impl.IllegalValueCheckImpl;
import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author futao
 * Created on 2018/9/25.
 */
//关联解析类, 校验逻辑
@Constraint(validatedBy = IllegalValueCheckImpl.class)
@Target(value = {ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface IllegalValueCheck {

    /**
     * 拦截的字符串
     *
     * @return
     */
    String forbidden() default "mmp";

    /**
     * 验证不通过时的错误提示信息
     *
     * @return
     */
    String message() default ErrorMessage.CONTAIN_ILLEGAL_PARAMETER;

    Class<?>[] groups() default {};

    /**
     * 变量名称 payload不可变
     * 否则会抛出异常`javax.validation.ConstraintDefinitionException: HV000074`
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
