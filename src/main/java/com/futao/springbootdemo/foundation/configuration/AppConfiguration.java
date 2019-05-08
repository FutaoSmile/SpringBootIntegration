package com.futao.springbootdemo.foundation.configuration;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author futao
 * Created on 2019-05-08.
 */
@Slf4j
@Configuration
public class AppConfiguration {

    @Bean
    public ThreadPoolExecutor a() {
        ThreadFactoryBuilder threadFactoryBuilder = new ThreadFactoryBuilder();
        threadFactoryBuilder.setNameFormat("wlb-tpe-%s");
        ThreadFactory threadFactory = threadFactoryBuilder.build();
        return new ThreadPoolExecutor(
                4,
                8,
                60,
                TimeUnit.SECONDS,
                //可被阻塞的消息数量
                new LinkedBlockingQueue<>(1024),
                threadFactory,
                (r, e) -> log.error("线程池ThreadPoolExecutor发生异常{}", e));
    }

}
