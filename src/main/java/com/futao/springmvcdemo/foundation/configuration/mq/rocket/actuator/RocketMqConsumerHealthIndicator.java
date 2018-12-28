package com.futao.springmvcdemo.foundation.configuration.mq.rocket.actuator;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;

/**
 * @author futao
 * Created on 2018-12-28.
 */
public class RocketMqConsumerHealthIndicator extends AbstractHealthIndicator {

    private DefaultMQPushConsumer defaultMQPushConsumer;

    public RocketMqConsumerHealthIndicator(DefaultMQPushConsumer defaultMQPushConsumer) {
        super("RocketMq defaultMQPushConsumer check fail");
        this.defaultMQPushConsumer = defaultMQPushConsumer;
    }

    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        builder.withDetail("nameServerAddr", this.defaultMQPushConsumer.getNamesrvAddr())
                .withDetail("messageModel", this.defaultMQPushConsumer.getMessageModel())
                .withDetail("consumeMessageBatchMaxSize", this.defaultMQPushConsumer.getConsumeMessageBatchMaxSize())
                .withDetail("pullBatchSize", this.defaultMQPushConsumer.getPullBatchSize())
                .withDetail("maxReconsumeTimes", this.defaultMQPushConsumer.getMaxReconsumeTimes())
                .withDetail("subscription", this.defaultMQPushConsumer.getSubscription())
                .withDetail("consumeFromWhere", this.defaultMQPushConsumer.getConsumeFromWhere())
                .withDetail("consumeThreadMin", this.defaultMQPushConsumer.getConsumeThreadMin())
                .withDetail("consumeThreadMax", this.defaultMQPushConsumer.getConsumeThreadMax())
                .withDetail("consumeTimeout", this.defaultMQPushConsumer.getConsumeTimeout());
        builder.up();
    }
}
