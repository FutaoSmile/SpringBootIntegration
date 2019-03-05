package com.futao.springbootdemo.suit.b;

/**
 * @author futao
 * Created on 2019-02-12.
 */
public class Impl implements Listenner {
    @Override
    public void action() {
        System.out.println(this.getClass().getSimpleName());
    }
}
