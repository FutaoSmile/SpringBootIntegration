package com.futao.springbootdemo.foundation.mq.active.topic;

import com.futao.springbootdemo.foundation.mq.active.ActiveMqConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * 订阅的队列是PTP模式还是Topic模式，与这边的定义无关。取决于配置
 * # 开启topic模式
 * spring:
 * jms:
 * pub-sub-domain: true
 *
 * @author futao
 * Created on 2019-06-04.
 */
@Slf4j
@Service
public class ActiveMqConsumer {

    /**
     * 订阅testTopic  -1
     *
     * @param mqMessage
     * @throws JMSException
     */
    @JmsListener(destination = ActiveMqConfig.TopicDefinition.activeTestTopic)
    public void testTopicConsumer1(ActiveMQMessage mqMessage) throws JMSException {
        String text = ((TextMessage) mqMessage.getMessage()).getText();
        log.info("testTopicConsumer1接收到activeMq-activeTestTopic消息:[{}]", text);
    }

    /**
     * 订阅testTopic  -2
     *
     * @param mqMessage
     * @throws JMSException
     */
    @JmsListener(destination = ActiveMqConfig.TopicDefinition.activeTestTopic)
    public void testTopicConsumer2(ActiveMQMessage mqMessage) throws JMSException {
        String text = ((TextMessage) mqMessage.getMessage()).getText();
        log.info("testTopicConsumer2接收到activeMq-activeTestTopic消息:[{}]", text);
    }

    /**
     * 订阅prodTopic  -1
     *
     * @param mqMessage
     * @throws JMSException
     */
    @JmsListener(destination = ActiveMqConfig.TopicDefinition.activeProdTopic)
    public void prodTopicConsumer1(ActiveMQMessage mqMessage) throws JMSException {
        String text = ((TextMessage) mqMessage.getMessage()).getText();
        log.info("prodTopicConsumer1接收到activeMq-activeProdTopic消息:[{}]", text);
    }

    /**
     * 订阅 prodTopic  -2
     *
     * @param mqMessage
     * @throws JMSException
     */
    @JmsListener(destination = ActiveMqConfig.TopicDefinition.activeProdTopic)
    public void prodTopicConsumer2(ActiveMQMessage mqMessage) throws JMSException {
        String text = ((TextMessage) mqMessage.getMessage()).getText();
        log.info("prodTopicConsumer2接收到activeMq-activeProdTopic消息:[{}]", text);
    }
}
