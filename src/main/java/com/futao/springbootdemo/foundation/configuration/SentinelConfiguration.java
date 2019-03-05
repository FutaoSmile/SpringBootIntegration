package com.futao.springbootdemo.foundation.configuration;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * Created on 2019-01-03.
 */
@Component
public class SentinelConfiguration {


    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }
}
