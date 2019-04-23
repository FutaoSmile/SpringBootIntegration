package com.futao.springbootdemo.foundation.mq.rocket.actuator;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
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
@ConditionalOnClass(DefaultMQPushConsumer.class)
@ConditionalOnBean(DefaultMQPushConsumer.class)
@ConditionalOnEnabledHealthIndicator("rocketMqConsumer")
@AutoConfigureBefore(HealthIndicatorAutoConfiguration.class)
public class RocketMqConsumerHealthIndicatorAutoConfiguration extends
        CompositeHealthIndicatorConfiguration<RocketMqConsumerHealthIndicator, DefaultMQPushConsumer> {

    private final Map<String, DefaultMQPushConsumer> defaultMQPushConsumerMap;

    public RocketMqConsumerHealthIndicatorAutoConfiguration(
            ObjectProvider<Map<String, DefaultMQPushConsumer>> defaultMQPushConsumerMap) {
        this.defaultMQPushConsumerMap = defaultMQPushConsumerMap.getIfAvailable();
    }

    @Bean
    @ConditionalOnMissingBean(name = "rocketMqConsumerHealthIndicator")
    public HealthIndicator rocketMqConsumerHealthIndicator() {
        return createHealthIndicator(this.defaultMQPushConsumerMap);
    }

}
