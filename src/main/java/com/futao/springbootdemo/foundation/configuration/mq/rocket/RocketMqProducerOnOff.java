package com.futao.springbootdemo.foundation.configuration.mq.rocket;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

/**
 * @author futao
 * Created on 2018-12-27.
 */
@Component
@ConfigurationProperties(prefix = "rocketmq.producer")
public class RocketMqProducerOnOff implements Condition {

    private static final String ON = "ON";
    private static final String OFF = "OFF";

    private String onOff;

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
//        return ON.equals(onOff);
        return Boolean.valueOf(context.getEnvironment().getProperty("rocketmq.producer.onOff"));
    }

    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }
}
