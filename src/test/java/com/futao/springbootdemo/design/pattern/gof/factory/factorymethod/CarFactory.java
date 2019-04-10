package com.futao.springbootdemo.design.pattern.gof.factory.factorymethod;

import com.futao.springbootdemo.design.pattern.gof.factory.Car;

/**
 * 工厂方法模式
 * 符合开闭原则，如果要进行扩展，只需新建一个工厂
 * 缺点：代码量增大，臃肿
 *
 * @author futao
 * Created on 2019-04-10.
 */
public interface CarFactory {

    Car createCar();

}
