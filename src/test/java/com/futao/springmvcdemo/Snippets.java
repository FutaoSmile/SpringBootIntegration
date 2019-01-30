package com.futao.springmvcdemo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * 有意思的代码片段
 *
 * @author futao
 * Created on 2019-01-16.
 */
public class Snippets {

    @Test
    public void test2() {
        outer:
        for (int i = 0; i < 100; i++) {
            in:
            for (int j = 0; j < 100; j++) {
                if (i == 1) {
                    break outer;
                }
                break in;
            }
        }
    }
//    @Test
//    public void test2() {
//        TimerTask task = new TimerTask() {
//            @Override
//            public void run() {
//                System.out.println("-");
//            }
//        };
//        /**
//         * 使用的时候会在主线程之外起一个单独的线程执行指定的计划任务，可以指定执行一次或者反复执行多次
//         * 是否设置为守护进程
//         */
//        new Timer().schedule(task, 0, 2000);
//    }


    /**
     * 让当前线程sleep一会
     */
    @Test
    public void test1() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
