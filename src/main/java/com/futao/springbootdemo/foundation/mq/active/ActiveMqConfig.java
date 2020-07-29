package com.futao.springbootdemo.foundation.mq.active;

import com.futao.springbootdemo.foundation.mq.active.ptp.ActiveMqQueueEnum;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

/**
 * @author futao
 * Created on 2019-06-04.
 */
@Configuration
public class ActiveMqConfig {

    /**
     * The ActiveMQConnectionFactory creates ActiveMQ Connections.
     * The PooledConnectionFactory pools Connections.
     * If you only need to create one Connection and keep it around for a long time you don't need to pool.
     * If you tend to create many Connection instances over time then Pooling is better as connecting is a heavy operation and can be a performance bottleneck.
     * <p>
     * 可以在这里统一设置JmsTemplate的一些配置，也可以在具体使用到JmsTemplate的时候单独设置
     * JmsMessageTemplate是对JmsTemplate的进一步封装
     *
     * @param factory
     * @return
     */
//    @Primary
//    @Bean
    public JmsTemplate jmsTemplate(PooledConnectionFactory factory) {
        JmsTemplate jmsTemplate = new JmsTemplate();
        //关闭事物
        jmsTemplate.setSessionTransacted(false);
        //TODO 在此设置无效
//        jmsTemplate.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        jmsTemplate.setConnectionFactory(factory);
        return jmsTemplate;
    }


    /**
     * 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
     *
     * @param pooledConnectionFactory
     * @return
     */
    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(PooledConnectionFactory pooledConnectionFactory) {
        DefaultJmsListenerContainerFactory factory =
                new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(pooledConnectionFactory);
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        return factory;
    }


    /**
     * ActiveMQ topic的定义
     */
    public static class TopicDefinition {
        public static final String activeTestTopic = "active-test-topic";
        public static final String activeProdTopic = "active-prod-topic";
    }

    /**
     * 定义一个名为BeanName为activeTestTopic的Topic:active-test-topic
     *
     * @return
     */
    @Bean(name = "activeTestTopic")
    public ActiveMQTopic activeMQTestTopic() {
        return new ActiveMQTopic(TopicDefinition.activeTestTopic);
    }

    /**
     * 定义一个名为BeanName为activeProdTopic的Topic:active-prod-topic
     *
     * @return
     */
    @Bean(name = "activeProdTopic")
    public ActiveMQTopic activeMQProdTopic() {
        return new ActiveMQTopic(TopicDefinition.activeProdTopic);
    }

    @Bean(name = ActiveMqQueueEnum.testQueue)
    public ActiveMQQueue activeTestQueue() {
        return new ActiveMQQueue(ActiveMqQueueEnum.TEST_QUEUE.getQueueName());
    }

}
