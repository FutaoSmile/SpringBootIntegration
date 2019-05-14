package com.futao.springbootdemo;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author futao
 * Created on 2019-05-13.
 */
public class LeetCode {
    @Test
    public void test1() {
        int i = 0;
        System.out.println(i++);
        System.out.println(++i);
    }

    public int numJewelsInStones1(String J, String S) {
        char[] baoShi = J.toCharArray();
        char[] shiTouArray = S.toCharArray();
        int count = 0;
        LinkedList<Character> linkedList = new LinkedList<>();
        for (char c : shiTouArray) {
            linkedList.add(c);
        }
        for (int i = 0; i < baoShi.length; i++) {
            char c = baoShi[i];
            for (int j = 0; j < linkedList.size(); j++) {
                if (linkedList.get(j).equals(c)) {
                    count++;
                    linkedList.remove(j);
                    --j;
                }
            }
        }
        return count;
    }

    public int numJewelsInStones2(String J, String S) {
        char[] shiTouArray = S.toCharArray();
        int count = 0;
        for (int i = 0; i < shiTouArray.length; i++) {
            if (J.indexOf(String.valueOf(shiTouArray[i])) != -1) {
                count++;
            }
        }
        return count;
    }
}
