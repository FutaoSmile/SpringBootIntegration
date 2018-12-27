package com.futao.springmvcdemo.design.pattern.gof.a.singleton;

/**
 * 3
 * 静态内部类模式
 * 线程安全，调用效率高，并且实现了延时加载
 *
 * @author futao
 * Created on 2018-12-25.
 */
public class SingletonStaticInnerClass {

    /**
     * 静态内部类并不会在类一开始加载的时候就加载
     * 要等到真正调用的时候才会加载
     * 又因为类加载是天然的线程安全的，所以不会有线程安全问题
     */
    private static class SingletonInnerClass {
        private static final SingletonStaticInnerClass instance = new SingletonStaticInnerClass();
    }

    public static SingletonStaticInnerClass getInstance() {
        return SingletonInnerClass.instance;
    }

    private SingletonStaticInnerClass() {
    }
}
