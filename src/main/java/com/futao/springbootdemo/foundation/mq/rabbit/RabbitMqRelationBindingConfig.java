package com.futao.springbootdemo.foundation.mq.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * RabbitMq关系绑定配置
 *
 * @author futao
 * Created on 2019-04-19.
 */
@Profile("dev")
@Configuration
public class RabbitMqRelationBindingConfig {

    /**
     * 定义一个队列
     *
     * @return
     */
    @Bean("topicQueue")
    public Queue topicQueue() {
        return new Queue(RabbitMqQueueEnum.TOPIC_QUEUE.getQueueName());
    }

    /**
     * 定义一个TopicExchange
     *
     * @return
     */
    @Bean("topicExchange")
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMqExchangeEnum.TOPIC_EXCHANGE.getExchangeName());
    }

    /**
     * 消息队列与Exchange进行绑定
     *
     * @param queue    消息队列
     * @param exchange 交换器
     * @return
     */
    @Bean("binding")
    public Binding binding(
            @Qualifier("topicQueue")
            @Autowired Queue queue,
            @Qualifier("topicExchange")
            @Autowired TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("foo.bar.#");
    }


    /**
     * topicBakQueue
     *
     * @return
     */
    @Bean("topicQueueBak")
    public Queue topicBakQueue() {
        return new Queue(RabbitMqQueueEnum.TOPIC_QUEUE_BAK.getQueueName());
    }

    /**
     * topicExchangeBak
     *
     * @return
     */
    @Bean("topicExchangeBak")
    public TopicExchange topicExchangeBak() {
        return new TopicExchange(RabbitMqExchangeEnum.TOPIC_EXCHANGE_BAK.getExchangeName());
    }

    /**
     * Bak绑定
     *
     * @param queue
     * @param exchange
     * @return
     */
    @Bean("bindingBak")
    public Binding bindingBak(
            @Qualifier("topicQueueBak")
            @Autowired Queue queue,
            @Qualifier("topicExchangeBak")
            @Autowired TopicExchange exchange
    ) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("topic.#.bak");
    }


    //    @Bean
    public SimpleMessageListenerContainer simpleMessageListenerContainer(ConnectionFactory connectionFactory, MessageListenerAdapter messageListenerAdapter) {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer(connectionFactory);
        simpleMessageListenerContainer.setQueueNames(RabbitMqQueueEnum.TOPIC_QUEUE.getQueueName());
        simpleMessageListenerContainer.setMessageListener(messageListenerAdapter);
        return simpleMessageListenerContainer;
    }

    //    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }
}

