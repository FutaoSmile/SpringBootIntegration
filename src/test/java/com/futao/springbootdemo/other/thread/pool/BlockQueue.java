package com.futao.springbootdemo.other.thread.pool;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author futao
 * Created on 2019-05-07.
 */
public class BlockQueue {
    public static void main(String[] args) {
        ConcurrentLinkedDeque<String> linkedDeque = new ConcurrentLinkedDeque<>();
        linkedDeque.offer("java");
        linkedDeque.offer("html");
        linkedDeque.offer("css");
        System.out.println(linkedDeque.poll());
        System.out.println(linkedDeque.poll());
        System.out.println(linkedDeque.size());
        System.out.println(linkedDeque.poll());


        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(3);
        linkedBlockingQueue.add("1");
        linkedBlockingQueue.add("2");
        linkedBlockingQueue.add("3");
        linkedBlockingQueue.add("4");
    }
}
