package com.futao.springbootdemo.foundation.mq.active.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * @author futao
 * Created on 2019-06-04.
 */
@Service
@Slf4j
public class ActiveMqProducer {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    /**
     * 目的地-Topic
     */
    @Qualifier("activeProdTopic")
    @Autowired
    private Destination activeMQProdTopic;


    public void send(String msg) {
        jmsMessagingTemplate.convertAndSend(activeMQProdTopic, msg);
        log.info("发送消息:[{}]", msg);
    }
}
