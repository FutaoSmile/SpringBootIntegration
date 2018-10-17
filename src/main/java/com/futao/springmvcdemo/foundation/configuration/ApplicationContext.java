package com.futao.springmvcdemo.foundation.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取Spring容器
 *
 * @author futao
 * Created on 2018/10/3.
 */
@Component
public class ApplicationContext implements ApplicationContextAware {
    private org.springframework.context.ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(org.springframework.context.ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 获取容器
     *
     * @return
     */
    public org.springframework.context.ApplicationContext get() {
        return this.applicationContext;
    }
}
