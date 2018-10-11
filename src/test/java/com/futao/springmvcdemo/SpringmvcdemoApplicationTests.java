package com.futao.springmvcdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmvcdemoApplicationTests {

    @Value("${jianshu.id}")
    private String id;

    @Test
    public void test10() {
        System.out.println(id);
    }

}
