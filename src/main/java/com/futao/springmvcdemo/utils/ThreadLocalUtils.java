package com.futao.springmvcdemo.utils;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * Created on 2018/9/19-16:52.
 * threadLocal工具类
 */
@Component
public class ThreadLocalUtils<T> {
    private ThreadLocal<T> threadLocal = new ThreadLocal<>();

    /**
     * 将o放入threadLocal中
     *
     * @param o
     */
    public void set(T o) {
        threadLocal.set(o);
    }

    /**
     * 获取threadLocal中的值
     *
     * @return
     */
    public T get() {
        T t = threadLocal.get();
        if (t == null) {
            throw LogicException.le(ErrorMessage.NOT_LOGIN);
        }
        return t;
    }

    /**
     * 移除threadLocal中的值，防止内存泄漏
     */
    public void remove() {
        threadLocal.remove();
    }
}
