package com.futao.springbootdemo.design.pattern.gof.singleton;

import java.io.Serializable;

/**
 * 单例模式2-懒汉模式
 * 只有在用到的时候才实例化对象
 *
 * @author futao
 * Created on 2018-12-25.
 */
public class LazySingleton implements Serializable {
    private static LazySingleton instance;

    /**
     * 1防止反射创建对象
     */
    private LazySingleton() {
        if (instance != null) {
            throw new RuntimeException("单例对象不允许创建多个实例对象");
        }
    }

    /**
     * 2防止反序列化创建对象
     * 在jdk中ObjectInputStream的类中有readUnshared（）方法，
     * 如果被反序列化的对象的类存在readResolve这个方法，
     * 他会调用这个方法来返回一个“array”（我也不明白），
     * 然后浅拷贝一份，作为返回值，并且无视掉反序列化的值，即使那个字节码已经被解析。
     *
     * @return
     */
    private Object readResolve() {
        return instance;
    }

    /**
     * 会有线程安全问题，所以需要加上同步锁
     * 因为这种方式，同时只能被一个线程访问，其他线程都会被阻塞，所以多线程环境下获取对象的速度非常慢
     *
     * @return 单例对象
     */
    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}
