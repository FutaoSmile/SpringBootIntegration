package com.futao.springmvcdemo.suit.b;

/**
 * @author futao
 * Created on 2019-02-12.
 */
public class Executor {

    void doAction(Listenner listenner) {
        listenner.action();
    }
}

class Test {
    public static void main(String[] args) {
        Executor executor = new Executor();
        executor.doAction(new Impl());
        executor.doAction(new Impl2());
    }
}
