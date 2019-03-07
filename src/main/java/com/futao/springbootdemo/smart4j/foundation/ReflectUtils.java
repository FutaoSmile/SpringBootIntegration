package com.futao.springbootdemo.smart4j.foundation;

import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.system.ErrorMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author futao
 * Created on 2019-01-25.
 */
public class ReflectUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectUtils.class);

    public Object newInstance(Class<?> aClass) {
        Object instance = null;
        try {
            instance = aClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            LOGGER.error("实例化【{}】失败: " + e.getMessage(), aClass, e);
            throw ApplicationException.le(ErrorMessage.ApplicationErrorMessage.SYSTEM_EXCEPTION, new String[]{aClass.getName()});
        }
        return instance;
    }
}
