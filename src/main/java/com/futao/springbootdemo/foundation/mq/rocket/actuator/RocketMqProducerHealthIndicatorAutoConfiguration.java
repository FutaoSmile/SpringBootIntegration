package com.futao.springbootdemo.foundation.mq.rocket.actuator;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.actuate.autoconfigure.health.CompositeHealthIndicatorConfiguration;
import org.springframework.boot.actuate.autoconfigure.health.ConditionalOnEnabledHealthIndicator;
import org.springframework.boot.actuate.autoconfigure.health.HealthIndicatorAutoConfiguration;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author futao
 * Created on 2018-12-28.
 */
@Configuration
@ConditionalOnClass(DefaultMQProducer.class)
@ConditionalOnBean(DefaultMQProducer.class)
@ConditionalOnEnabledHealthIndicator("rocketMqProducer")
@AutoConfigureBefore(HealthIndicatorAutoConfiguration.class)
//@AutoConfigureAfter(RocketMqProducerHealthIndicator.class)
public class RocketMqProducerHealthIndicatorAutoConfiguration extends
        CompositeHealthIndicatorConfiguration<RocketMqProducerHealthIndicator, DefaultMQProducer> {

    private final Map<String, DefaultMQProducer> defaultMQProducerMap;

    public RocketMqProducerHealthIndicatorAutoConfiguration(
            ObjectProvider<Map<String, DefaultMQProducer>> defaultMQProducerMap) {
        this.defaultMQProducerMap = defaultMQProducerMap.getIfAvailable();
    }

    @Bean
    @ConditionalOnMissingBean(name = "rocketMqProducerHealthIndicator")
    public HealthIndicator rocketMqProducerHealthIndicator() {
        return createHealthIndicator(this.defaultMQProducerMap);
    }

}
