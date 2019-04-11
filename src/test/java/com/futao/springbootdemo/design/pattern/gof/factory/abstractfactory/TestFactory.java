package com.futao.springbootdemo.design.pattern.gof.factory.abstractfactory;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public class TestFactory {

    public static void main(String[] args) {
        CarFactory factory = new SeniorCarFactory();
        Engine engine = factory.createEngine();
        engine.run();
        engine.start();

        StringBuilder stringBuilder = new StringBuilder();
    }
}
