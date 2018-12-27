package com.futao.springmvcdemo.design.pattern.gof.a.singleton;

/**
 * 4
 * 避免了反射与反序列化的漏洞
 * 但是没有懒加载的效果
 *
 * @author futao
 * Created on 2018-12-25.
 */
public enum SingletonEnum {

    //这个枚举元素本身就是单例的
    INSTANCE;
}
