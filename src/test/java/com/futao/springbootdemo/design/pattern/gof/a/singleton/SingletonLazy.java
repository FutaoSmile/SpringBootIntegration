package com.futao.springbootdemo.design.pattern.gof.a.singleton;

/**
 * 2
 * 单例模式-懒汉模式
 * 只有在用到的时候才实例化对象
 *
 * @author futao
 * Created on 2018-12-25.
 */
public class SingletonLazy {
    private static SingletonLazy instance = null;

    private SingletonLazy() {
    }

    /**
     * 会有线程安全问题，所以需要加上同步锁
     * 因为这种方式，同时只能被一个线程访问，其他线程都会被阻塞，所以获取对象的速度非常慢
     *
     * @return
     */
    public static synchronized SingletonLazy getInstance() {
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }
}
