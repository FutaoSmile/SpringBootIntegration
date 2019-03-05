package com.futao.springbootdemo.threads;

/**
 * 如果发生了异常且未被try catch，锁会被释放
 *
 * @author futao
 * Created on 2019-01-15.
 */
public class T implements Runnable {

    private int count = 10;

    /**
     * 不加 synchronized(this)会产生线程不安全问题。
     */
    private synchronized void test() {
        count--;
        try {
            if (count == 5) {
                throw new NullPointerException();
            }
        } catch (Exception e) {
            System.out.println("-");
        }
        System.out.println("当前count值为:" + count + ";线程为:" + Thread.currentThread().getName());
    }

    @Override
    public void run() {
        test();
    }

    public static void main(String[] args) {
        T t = new T();
        for (int i = 0; i < 10; i++) {
            new Thread(t).start();
        }
    }
}
