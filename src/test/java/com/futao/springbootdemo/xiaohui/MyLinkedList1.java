package com.futao.springbootdemo.xiaohui;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 单向链表
 *
 * @author futao
 * Created on 2019-05-22.
 */
public class MyLinkedList1 {
    /**
     * 链表的每一个节点
     */
    @Getter
    @Setter
    @ToString
    private static class Node {
        /**
         * 当前节点存储的数据
         */
        private int data;
        /**
         * 下一个节点
         */
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }

    private Node head;


    public void insert(int index, int data) {

    }

    public static void main(String[] args) {
        LinkedList<String> strings = new LinkedList<>();
        strings.add("1111");
        strings.add(1,"2");
        strings.add(1,"2");
        strings.add(1,"2");
        System.out.println(strings);


        ArrayList<String> strings1 = new ArrayList<>();
        strings1.add("1");
        strings1.add(2,"2");
    }

}
