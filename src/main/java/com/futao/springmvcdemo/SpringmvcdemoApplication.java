package com.futao.springmvcdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author futao
 * ServletComponentScan 开启servlet和filter
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springmvcdemo.dao")
//@EnableAspectJAutoProxy
public class SpringmvcdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringmvcdemoApplication.class, args);
    }
}
