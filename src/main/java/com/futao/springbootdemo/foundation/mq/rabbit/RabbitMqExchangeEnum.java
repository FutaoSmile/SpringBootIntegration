package com.futao.springbootdemo.foundation.mq.rabbit;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * RabbitMq-Exchange交换器定义
 *
 * @author futao
 * Created on 2019-04-23.
 */
@AllArgsConstructor
@Getter
public enum RabbitMqExchangeEnum {
    /**
     * SpringBoot-topic交换器
     */
    TOPIC_EXCHANGE("sb-exchange-topic");
    private String exchangeName;
}
