package com.futao.springmvcdemo;

import com.alibaba.fastjson.parser.ParserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

/**
 * @author futao
 * ServletComponentScan 开启servlet和filter
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springmvcdemo.dao")
@EnableCaching
//@EnableAspectJAutoProxy
public class SpringmvcdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringmvcdemoApplication.class, args);
        /**
         * redis反序列化
         * 开启fastjson反序列化的autoType
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}
