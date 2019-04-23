package com.futao.springbootdemo.foundation.mq.rabbit;

import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * @author futao
 * Created on 2019-04-19.
 */
@Component
public class Receiver {

    private CountDownLatch latch = new CountDownLatch(1);

    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }

}
