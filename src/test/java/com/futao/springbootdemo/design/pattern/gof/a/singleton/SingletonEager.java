package com.futao.springbootdemo.design.pattern.gof.a.singleton;

/**
 * 1
 * 单例模式：只有一个对象，提供一个方法来提供对对象的访问
 * 单例模式-饿汉模式，即在类加载的时候就实例化对象。
 *
 * @author futao
 * Created on 2018-12-25.
 */
public class SingletonEager {
    //因为该字段是静态的，属于类，所以会在类加载的时候就初始化，又因为类加载的时候是天然的线程安全的，所以不会有线程安全问题
    private static SingletonEager instance = new SingletonEager();

    //私有构造方法，防止用户随意new对象
    private SingletonEager() {
    }

    //获取单例的静态方法,对于需要频繁访问的对象使用这种方式比较好
    public static SingletonEager getInstance() {
        return instance;
    }
}
