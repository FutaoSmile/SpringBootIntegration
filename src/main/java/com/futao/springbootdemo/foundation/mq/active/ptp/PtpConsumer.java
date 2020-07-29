package com.futao.springbootdemo.foundation.mq.active.ptp;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * @author futao
 * Created on 2019-06-06.
 */
@Slf4j
@Service
public class PtpConsumer {

    @JmsListener(destination = ActiveMqQueueEnum.testQueue, containerFactory = "jmsQueueListener")
    public void ptpConsumer1(ActiveMQMessage message) throws JMSException {
        String text = ((TextMessage) message).getText();
        if ("节日快乐666".equalsIgnoreCase(text)) {
            message.acknowledge();    //ack手动确认
        }
        log.info("【1】receive message from activeMQ :[{}]", text);
    }

    @JmsListener(destination = ActiveMqQueueEnum.testQueue, containerFactory = "jmsQueueListener")
    public void ptpConsumer2(ActiveMQMessage message) throws JMSException {
        String text = ((TextMessage) message).getText();
        if ("节日快乐666".equalsIgnoreCase(text)) {
            message.acknowledge();    //ack手动确认
        }
        log.info("【2】receive message from activeMQ :[{}]", text);
    }


    @Test
    public void test() throws Exception {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);//开启ack手动确认
        MessageConsumer consumer = session.createConsumer(new ActiveMQQueue(ActiveMqQueueEnum.TEST_QUEUE.getQueueName()));
        connection.start();
        consumer.setMessageListener(message -> {
            try {
                String text = ((TextMessage) message).getText();
                System.out.println(("收到消息:{}" + text));
                if ("节日快乐666".equalsIgnoreCase(text)) {
                    message.acknowledge();    //ack手动确认
                }
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
        Thread.sleep(999999999);
    }
}
