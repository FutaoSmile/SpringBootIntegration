package com.futao.springbootdemo.design.pattern.gof.singleton.byself;

/**
 * 单例模式1-饿汉模式
 *
 * @author futao
 * Created on 2019-04-03.
 */
public class EagerSingleton {

    /**
     * 伴随着类的加载而实例化一个对象，如果该单例最后并未被使用，则浪费了系统资源
     */
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() {
    }

    /**
     * 为什么不设置成final的，因为静态方法没必要设置成final的
     *
     * @return 单例
     */
    public static EagerSingleton getInstance() {
        return instance;
    }


}
