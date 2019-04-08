package com.futao.springbootdemo.threads;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019-04-01.
 */
public class Ta extends Thread {
    @Override
    public void run() {
        System.out.println(this.getClass());
    }

    public static void main(String[] args) throws InterruptedException {
        Tb tb = new Tb();
        Thread thread = new Thread(tb);
        thread.start();
        Ta ta = new Ta();
        ta.start();
    }
}


class Tb implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getClass().getName());
    }
}

class Tc implements Callable {

    @Override
    public Object call() throws Exception {
        System.out.println(this.getClass().getName());
        return null;
    }
}