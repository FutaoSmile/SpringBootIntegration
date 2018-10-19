package com.futao.springmvcdemo.service

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer
import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.springframework.context.annotation.Bean

interface RocketMqService {

    @Bean
    fun producer(): DefaultMQProducer

    @Bean
    fun consumer(): DefaultMQPushConsumer
}
