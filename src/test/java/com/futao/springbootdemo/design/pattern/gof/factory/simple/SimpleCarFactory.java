package com.futao.springbootdemo.design.pattern.gof.factory.simple;

import com.futao.springbootdemo.design.pattern.gof.factory.Audi;
import com.futao.springbootdemo.design.pattern.gof.factory.Byd;
import com.futao.springbootdemo.design.pattern.gof.factory.Car;

/**
 * 简单工厂模式(静态工厂模式)
 * 缺点：不易扩展
 * <p>
 * 开闭原则 OCP OpenClosePrinciple对扩展开放，对修改关闭
 *
 * @author futao
 * Created on 2019-04-10.
 */
public class SimpleCarFactory {

    public static Car createCar(String type) {
        switch (type) {
            case "audi":
                return new Audi();
            case "byd":
                return new Byd();
            default:
                return null;
        }
    }
}
