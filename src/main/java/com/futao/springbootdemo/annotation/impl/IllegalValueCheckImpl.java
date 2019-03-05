package com.futao.springbootdemo.annotation.impl;

import com.futao.springbootdemo.annotation.IllegalValueCheck;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author futao
 * Created on 2018/9/25.
 * 敏感词检测
 */
public class IllegalValueCheckImpl implements ConstraintValidator<IllegalValueCheck, String> {
    private String forbidden;

    /**
     * 初始化
     *
     * @param constraintAnnotation
     */
    @Override
    public void initialize(IllegalValueCheck constraintAnnotation) {
        //获取禁止的词汇
        this.forbidden = constraintAnnotation.forbidden();
    }

    /**
     * 检验逻辑
     *
     * @param value
     * @param context
     * @return
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!ObjectUtils.allNotNull(value)) {
            return true;
        }
        return !value.contains(forbidden);
    }
}
