package com.futao.springmvcdemo.utils;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;

import java.lang.reflect.Method;

/**
 * @author futao
 * Created on 2019-01-30.
 */
public class CommonUtils {

    private static final String GETTER_PREFIX = "get";
    private static final String SETTER_PREFIX = "set";
    private static final String IS_PREFIX = "is";

    public static String getFieldName(Method method) {
        String name = method.getName();
        if (name.startsWith(GETTER_PREFIX) || name.startsWith(SETTER_PREFIX)) {
            return name.substring(3, 4).toLowerCase() + name.substring(4);
        } else if (name.startsWith(IS_PREFIX)) {
            return name.substring(2, 3).toLowerCase() + name.substring(3);
        } else {
            throw LogicException.le(ErrorMessage.LogicErrorMessage.FIELD_NO_GETTER_OR_SETTER);
        }
    }
}
