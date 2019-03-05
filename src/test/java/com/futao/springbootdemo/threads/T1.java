package com.futao.springbootdemo.threads;

import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019-01-15.
 */
public class T1 implements Runnable {

    private int count = 10;

    private synchronized void test() {
        while (true) {
            if (count == 5) {
                int c = 1 / 0;
            }
            System.out.println(Thread.currentThread().getName() + "----" + count);
            count--;
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        test();
    }


    public static void main(String[] args) {
        T1 t = new T1();
        new Thread(t, "t1").start();
        new Thread(t, "t2").start();

    }
}
