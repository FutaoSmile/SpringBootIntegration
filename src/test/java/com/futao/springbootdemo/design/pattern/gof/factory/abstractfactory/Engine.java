package com.futao.springbootdemo.design.pattern.gof.factory.abstractfactory;

/**
 * 引擎
 *
 * @author futao
 * Created on 2019-04-10.
 */
public interface Engine {
    void run();

    void start();
}


/**
 * 高端引擎
 */
class SeniorEngine implements Engine {

    @Override
    public void run() {
        System.out.println("好引擎，跑得快");
    }

    @Override
    public void start() {
        System.out.println("好引擎，启动快");
    }
}


/**
 * 低端引擎
 */
class JuniorEngine implements Engine {

    @Override
    public void run() {
        System.out.println("垃圾引擎，跑得慢");
    }

    @Override
    public void start() {
        System.out.println("垃圾引擎，启动不起来");
    }
}