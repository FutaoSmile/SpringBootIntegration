package com.futao.springbootdemo.design.pattern.gof.factory.abstractfactory;

/**
 * 座椅
 *
 * @author futao
 * Created on 2019-04-10.
 */
public interface Seat {
    void massage();
}

/**
 * 高端座椅
 */
class SeniorSeat implements Seat {

    @Override
    public void massage() {
        System.out.println("高端座椅，自动按摩");
    }
}


/**
 * 低端座椅
 */
class JuniorSeat implements Seat {

    @Override
    public void massage() {
        System.out.println("低端座椅，很垃圾");
    }
}