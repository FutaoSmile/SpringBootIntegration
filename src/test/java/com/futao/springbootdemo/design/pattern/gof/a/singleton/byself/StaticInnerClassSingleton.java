package com.futao.springbootdemo.design.pattern.gof.a.singleton.byself;

/**
 * 单例模式4-静态内部类
 * 线程安全，调用效率高，并且实现了延时加载
 *
 * @author futao
 * Created on 2019-04-03.
 */
public class StaticInnerClassSingleton {

    private StaticInnerClassSingleton() {

    }

    /**
     * 静态内部类并不会在类一开始加载的时候就加载
     * 要等到真正调用的时候才会加载
     * 又因为类加载是天然的线程安全的，所以不会有线程安全问题
     */
    private static class StaticInnerClass {
        private static final StaticInnerClassSingleton instance = new StaticInnerClassSingleton();
    }

    public static StaticInnerClassSingleton getInstance() {
        return StaticInnerClass.instance;
    }
}
