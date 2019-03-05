package com.futao.springbootdemo.annotation.impl;

import com.futao.springbootdemo.annotation.EnumStatus;
import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.enums.face.IEnum;
import com.futao.springbootdemo.model.system.ErrorMessage;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 枚举值合法性验证实现部分
 *
 * @author futao
 * Created on 2019-02-22.
 */
public class EnumStatusImpl implements ConstraintValidator<EnumStatus, Object> {

    private static HashMap<Class, String[]> cache = new HashMap<>();

    private String[] currentEnumStatus;


    @Override
    public void initialize(EnumStatus constraintAnnotation) {
        System.out.println(StringUtils.repeat("-", 100));
        Class enumClass = constraintAnnotation.value();
        if (cache.containsKey(enumClass)) {
            currentEnumStatus = cache.get(enumClass);
        } else {
            try {
                Method method = enumClass.getMethod(IEnum.class.getMethods()[0].getName());
                Object[] enumConstants = enumClass.getEnumConstants();
                String[] enumStatusArray = new String[enumConstants.length];
                cache.put(enumClass, enumStatusArray);
                for (int i = 0; i < enumConstants.length; i++) {
                    enumStatusArray[i] = method.invoke(enumConstants[i]).toString();
                }
                currentEnumStatus = cache.get(enumClass);
            } catch (Exception e) {
                throw ApplicationException.le(ErrorMessage.ApplicationException.ENUM_MUST_IMPL_IENUM);
            }
        }
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return Arrays.asList(currentEnumStatus).contains(value.toString());
    }
}
