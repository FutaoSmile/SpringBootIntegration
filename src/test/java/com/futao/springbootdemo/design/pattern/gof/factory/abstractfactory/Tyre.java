package com.futao.springbootdemo.design.pattern.gof.factory.abstractfactory;

/**
 * 轮胎
 *
 * @author futao
 * Created on 2019-04-10.
 */
public interface Tyre {
    void revolve();
}


/**
 * 高端轮胎
 */
class SeniorTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("高端轮胎，呼呼呼");
    }
}

/**
 * 低端轮胎
 */
class JuniorTyre implements Tyre {

    @Override
    public void revolve() {
        System.out.println("垃圾轮胎，卡卡卡");
    }
}
