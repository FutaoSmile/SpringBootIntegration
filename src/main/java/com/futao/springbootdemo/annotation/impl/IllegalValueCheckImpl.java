package com.futao.springbootdemo.annotation.impl;

import com.futao.springbootdemo.annotation.IllegalValueCheck;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 敏感词检测
 *
 * @author futao
 * Created on 2018/9/25.
 */
public class IllegalValueCheckImpl implements ConstraintValidator<IllegalValueCheck, String> {
    /**
     * 禁止的词汇
     */
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
     * @param value   用户输入的数据
     * @param context 上下文
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
