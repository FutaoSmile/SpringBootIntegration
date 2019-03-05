package com.futao.springbootdemo.smart4j.foundation;

import com.futao.springbootdemo.smart4j.annotation.SmartController;
import com.futao.springbootdemo.smart4j.annotation.SmartService;

import java.util.HashSet;
import java.util.Set;

/**
 * 类操作助手类
 *
 * @author futao
 * Created on 2019-01-05.
 */
public class ClassHelper {

    private final static String BASE_PACKAGE_NAME = "com.futao.springbootdemo.smart4j";

    /**
     * 存放加载的类
     */
    private static final Set<Class<?>> CLASS_SET = ClassUtils.getClassSet(BASE_PACKAGE_NAME);


    /**
     * 获取应用包下的所有类
     *
     * @return
     */
    public static Set<Class<?>> getClassSet() {
        return CLASS_SET;
    }

    /**
     * 获取标记了某个注解的类的集合
     *
     * @param annotation 注解
     * @return
     */
    public static Set<Class<?>> getClassSetByAnn(Class annotation) {
        return (Set<Class<?>>) CLASS_SET.stream().filter(it -> it.isAnnotationPresent(annotation));
    }

    /**
     * 获取容器下所有的bean
     */
    public static Set<Class<?>> getBeans() {
        Set<Class<?>> classSet = new HashSet<>();
        Class[] beanAnnos = new Class[]{SmartController.class, SmartService.class};
        for (Class beanAnno : beanAnnos) {
            classSet.addAll(getClassSetByAnn(beanAnno));
        }
        return classSet;
    }
}
