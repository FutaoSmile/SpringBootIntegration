package com.futao.springbootdemo.foundation.mq.rabbit;

import com.alibaba.fastjson.JSON;
import com.futao.springbootdemo.model.entity.Tag;
import com.futao.springbootdemo.model.entity.User;
import com.lazyer.foundation.foundation.FastJson2HttpMessageConverter;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消息接收者
 *
 * @author futao
 * Created on 2019-05-06.
 */
@Slf4j
@Component
@RabbitListener(queues = {RabbitMqQueueEnum.topicQueueBak})
public class RabbitMqReceiverBak {

    /**
     * 消费者(不同的@Payload可以用来处理不同的数据)
     *
     * @param user    消息体
     * @param tag     该消息在rabbitmq-Channel的唯一标识ID
     * @param channel 通道
     */
    @RabbitHandler
    public void process(@Payload User user, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) {
        log.info("接收到消息:[{}]", JSON.toJSONString(user, FastJson2HttpMessageConverter.SERIALIZER_FEATURES));
        try {
            //确认消息-手动ack（multiple批处理）
            channel.basicAck(tag, false);
            //否认消息，并且丢回消息队列
//            channel.basicNack(tag, false, true);
        } catch (IOException e) {
            log.error("消费消息回传确认ack失败", e);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 消费者
     *
     * @param tag     消息体
     * @param tag     该消息在rabbitmq-Channel的唯一标识ID
     * @param channel 通道
     */
    @RabbitHandler
    public void process(@Payload Tag tagData, @Header(AmqpHeaders.DELIVERY_TAG) long tag, Channel channel) {
        log.info("接收到消息:[{}]", JSON.toJSONString(tagData, FastJson2HttpMessageConverter.SERIALIZER_FEATURES));
        try {
            //确认消息-手动ack（multiple批处理）
            channel.basicAck(tag, false);
            //否认消息，并且丢回消息队列
//            channel.basicNack(tag, false, true);
        } catch (IOException e) {
            log.error("消费消息回传确认ack失败", e);
        }
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
