package com.futao.springbootdemo.design.pattern.gof.factory.abstractfactory;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public interface CarFactory {

    Engine createEngine();

    Seat createSeat();

    Tyre createTyre();
}


/**
 * 高端汽车工厂
 */
class SeniorCarFactory implements CarFactory {


    @Override
    public Engine createEngine() {
        return new SeniorEngine();
    }

    @Override
    public Seat createSeat() {
        return new SeniorSeat();
    }

    @Override
    public Tyre createTyre() {
        return new SeniorTyre();
    }
}


/**
 * 低端汽车工厂
 */
class JuniorCarFactory implements CarFactory {


    @Override
    public Engine createEngine() {
        return new JuniorEngine();
    }

    @Override
    public Seat createSeat() {
        return new JuniorSeat();
    }

    @Override
    public Tyre createTyre() {
        return new JuniorTyre();
    }
}