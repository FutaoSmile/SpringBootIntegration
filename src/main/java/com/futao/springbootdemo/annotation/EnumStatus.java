package com.futao.springbootdemo.annotation;

import com.futao.springbootdemo.annotation.impl.EnumStatusImpl;
import com.futao.springbootdemo.model.system.ErrorMessage;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 枚举值合法性验证标记
 *
 * @author futao
 * Created on 2019-02-22.
 */
@Constraint(validatedBy = EnumStatusImpl.class)
@Target(value = {ElementType.PARAMETER, ElementType.FIELD})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface EnumStatus {
    /**
     * 限定范围
     *
     * @return
     */
    Class value();

    /**
     * 验证不通过时的错误提示信息
     *
     * @return
     */
    String message() default ErrorMessage.LogicErrorMessage.ENUM_NOT_ALLOW;

    Class<?>[] groups() default {};

    /**
     * 变量名称 payload不可变
     * 否则会抛出异常`javax.validation.ConstraintDefinitionException: HV000074`
     *
     * @return
     */
    Class<? extends Payload>[] payload() default {};
}
