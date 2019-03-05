package com.futao.springbootdemo.smart4j.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author futao
 * Created on 2019-01-05.
 */
public final class ReflectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);


    /**
     * 创建实例
     *
     * @param aClass
     * @return
     */
    public static Object newInstance(Class<?> aClass) {
        Object instance = null;
        try {
            instance = aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("实例化对象失败:" + e.getMessage(), e);
        }
        return instance;
    }

    /**
     * 调用方法
     *
     * @param obj
     * @param method
     * @param args
     * @return
     */
    public static Object invokeMethod(Object obj, Method method, Object... args) {
        Object result = null;
        method.setAccessible(true);
        try {
            result = method.invoke(obj, args);
        } catch (IllegalAccessException | InvocationTargetException e) {
            LOGGER.error("执行方法失败:" + e.getMessage(), e);
        }
        return result;
    }

    /**
     * 设置成员变量的值
     *
     * @param obj
     * @param field
     * @param value
     */
    public static void setField(Object obj, Field field, Object value) {
        field.setAccessible(true);
        try {
            field.set(obj, value);
        } catch (IllegalAccessException e) {
            LOGGER.error("设值失败:" + e.getMessage(), e);
        }

    }
}