package com.futao.springbootdemo.foundation.mq.rocket.actuator;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author futao
 * Created on 2018-12-28.
 */
public class RocketMqProducerHealthIndicator extends AbstractHealthIndicator {

    private DefaultMQProducer defaultMQProducer;

    public RocketMqProducerHealthIndicator(DefaultMQProducer defaultMQProducer) {
        super("RocketMq defaultMQProducer check fail");
        this.defaultMQProducer = defaultMQProducer;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.withDetail("nameServerAddr", this.defaultMQProducer.getNamesrvAddr())
                .withDetail("producerGroup", this.defaultMQProducer.getProducerGroup())
                .withDetail("maxMessageSize", this.defaultMQProducer.getMaxMessageSize())
                .withDetail("retryTimesWhenSendFailed", this.defaultMQProducer.getRetryTimesWhenSendFailed())
                .withDetail("sendMsgTimeout", this.defaultMQProducer.getSendMsgTimeout());
        builder.up();
    }
}
