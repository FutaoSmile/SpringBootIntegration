package com.futao.springmvcdemo;

import com.alibaba.fastjson.parser.ParserConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author futao
 * ServletComponentScan 开启servlet和filter
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan("com.futao.springmvcdemo.dao")
@EnableCaching
@EnableScheduling
@EnableAsync
//@EnableAspectJAutoProxy
@EnableElasticsearchRepositories(basePackages = "com.futao.springmvcdemo")
public class SpringmvcdemoApplication {
    public static void main(String[] args) {
        /**
         * 添加elasticsearch之后发生异常的解决方案
         * Springboot整合Elasticsearch 在项目启动前设置一下的属性，防止报错
         * 解决netty冲突后初始化client时还会抛出异常
         * java.lang.IllegalStateException: availableProcessors is already set to [4], rejecting [4]
         */
        System.setProperty("es.set.netty.runtime.available.processors", "false");

        SpringApplication.run(SpringmvcdemoApplication.class, args);
        /**
         * redis反序列化
         * 开启fastjson反序列化的autoType
         */
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }
}
