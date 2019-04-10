package com.futao.springbootdemo.design.pattern.gof.singleton.byself;

/**
 * 单例模式2-懒汉模式
 *
 * @author futao
 * Created on 2019-04-03.
 */
public class LazySingleton {
    private static LazySingleton instance;

    private LazySingleton() {
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }


}
