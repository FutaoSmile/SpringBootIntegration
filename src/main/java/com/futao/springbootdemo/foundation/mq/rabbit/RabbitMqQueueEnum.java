package com.futao.springbootdemo.foundation.mq.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RabbitMq消息队列定义
 *
 * @author futao
 * Created on 2019-04-23.
 */
@AllArgsConstructor
@Getter
public enum RabbitMqQueueEnum {
    /**
     * SpringBoot-主题模式队列
     */
    TOPIC_QUEUE("sb-topic-queue");


    private String queueName;

}
