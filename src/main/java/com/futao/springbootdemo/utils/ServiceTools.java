package com.futao.springbootdemo.utils;

import com.futao.springbootdemo.foundation.LogicException;
import com.futao.springbootdemo.model.entity.BaseEntity;
import com.futao.springbootdemo.model.system.ErrorMessage;

/**
 * @author futao
 * Created on 2019-03-05.
 */
public class ServiceTools {
    public static <T extends BaseEntity> T checkSingleResultNull(T t) {
        if (t == null) {
            throw LogicException.le(ErrorMessage.LogicErrorMessage.RESULT_NULL);
        }
        return t;
    }
}
