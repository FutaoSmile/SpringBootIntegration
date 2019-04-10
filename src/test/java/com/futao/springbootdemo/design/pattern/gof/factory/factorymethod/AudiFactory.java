package com.futao.springbootdemo.design.pattern.gof.factory.factorymethod;

import com.futao.springbootdemo.design.pattern.gof.factory.Audi;
import com.futao.springbootdemo.design.pattern.gof.factory.Car;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public class AudiFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Audi();
    }
}
