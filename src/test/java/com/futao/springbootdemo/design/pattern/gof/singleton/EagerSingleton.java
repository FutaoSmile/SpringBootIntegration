package com.futao.springbootdemo.design.pattern.gof.singleton;

import java.io.Serializable;

/**
 * 单例模式1-饿汉模式，即在类加载的时候就实例化对象。
 *
 * @author futao
 * Created on 2018-12-25.
 */
public class EagerSingleton implements Serializable {
    /**
     * 因为该字段是静态的，属于类，所以会在类加载的时候就初始化，
     * 又因为类加载的时候是天然的线程安全的，所以不会有线程安全问题
     * <p>
     * 伴随着类的加载而实例化一个对象，如果该单例最后并未被使用，则浪费了系统资源
     */
    private static final EagerSingleton instance = new EagerSingleton();

    /**
     * 私有构造方法，防止用户随意new对象
     */
    private EagerSingleton() {
        if (instance != null) {
            //如果单例对象已经被创建，则不允许再调用构造方法创建对象
            throw new RuntimeException("不允许通过反射创建对象!");
        }
    }

    /**
     * 获取单例的静态方法,对于需要频繁访问的对象使用这种方式比较好
     *
     * @return 单例
     */
    public static EagerSingleton getInstance() {
        return instance;
    }

    /**
     * 防止反序列化创建对象
     * 在jdk中ObjectInputStream的类中有readUnshared（）方法，
     * 如果被反序列化的对象的类存在readResolve这个方法，
     * 他会调用这个方法来返回一个“array”
     * 然后浅拷贝一份，作为返回值，并且无视掉反序列化的值，即使那个字节码已经被解析。
     *
     * @return
     */
    private Object readResolve() {
        return instance;
    }
}
