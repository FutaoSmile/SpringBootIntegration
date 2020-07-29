package com.futao.springbootdemo.foundation.mq.active.ptp;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * PTP模式生产者
 *
 * @author futao
 * Created on 2019-06-06.
 */
@Slf4j
@Component
public class PtpProducer {


    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 目的地
     */
    @Qualifier("springboot-test-queue")
    @Autowired
    private ActiveMQQueue springBootTestQueue;


    public void send(String msg) {
        jmsMessagingTemplate.convertAndSend(springBootTestQueue, msg);
        try {
            log.info("send to ActiveMQ-Queue[{}] success ,msg：[{}]", springBootTestQueue.getQueueName(), msg);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
