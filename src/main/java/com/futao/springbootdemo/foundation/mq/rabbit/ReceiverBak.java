package com.futao.springbootdemo.foundation.mq.rabbit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

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
public class ReceiverBak {

    @RabbitHandler
    public void process(String msg) {
        log.info("接收到消息:[{}]", msg);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
