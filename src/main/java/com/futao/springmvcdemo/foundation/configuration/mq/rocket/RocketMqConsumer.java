package com.futao.springmvcdemo.foundation.configuration.mq.rocket;

import com.futao.springmvcdemo.foundation.configuration.mq.rocket.kt.MQConsumeMsgListenerProcessor;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * Rocket Mq 消费者
 *
 * @author futao
 * Created on 2018-12-27.
 */
@ConfigurationProperties(prefix = "rocketmq.consumer")
@Configuration
public class RocketMqConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMqConsumer.class);

    /**
     * 启动or停用
     */
    private String onOff;

    /**
     * 服务注册地址
     */
    private String nameServerAddr;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 该消费者订阅的主题和tags("*"号表示订阅该主题下所有的tags),格式：topic~tag1||tag2||tag3;topic2~*;
     */
    private String topics;

    /**
     * tags
     */
    private String tags;

    /**
     * 最小线程个数
     */
    private int consumeThreadMin;
    /**
     * 最大线程个数
     */
    private int consumeThreadMax;

    /**
     * 设置一次消费消息的条数，默认为1条
     */
    private int consumeMessageBatchMaxSize;

    /**
     * 消费失败重试次数
     */
    private int reConsumerTimes;

    /**
     * 是否启用vip netty通道以发送消息
     * <p>
     * broker的netty server会起两个通信服务
     * 两个服务除了服务的端口号不一样，其他都一样
     * 其中一个的端口（配置端口-2）作为vip通道，客户端可以启用本设置项把发送消息此vip通道。
     */
    private boolean vipChannelEnabled;


    @Resource
    private MQConsumeMsgListenerProcessor mqMessageListenerProcessor;

    /**
     * @return
     */
    @Bean
    @Conditional(RocketMqConsumerOnOff.class)
    public DefaultMQPushConsumer consumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();
        consumer.setNamesrvAddr(nameServerAddr);
        consumer.setConsumerGroup(groupName);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setVipChannelEnabled(vipChannelEnabled);
        //消费模式 集群还是广播，默认为集群(自动负载均衡)
        //广播消费： 消息会发给Consume Group中的每一个消费者进行消费,如果设置为广播消息会导致NOT_ONLINE异常,https://github.com/apache/rocketmq/issues/296
        consumer.setMessageModel(MessageModel.CLUSTERING);
        // 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
        // 如果非第一次启动，那么按照上次消费的位置继续消费
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        try {
            //订阅主题与tag
            consumer.subscribe(topics, tags);
            LOGGER.info("\n\nrocket mq consumer subscribe topics:【{}】 tags:【{}】 success\n", topics, tags);
        } catch (MQClientException e) {
            e.printStackTrace();
            LOGGER.error("start rocket mq consumer fail;\nmessage:{}\nerrorMessage:{}\n", e.getMessage(), e.getErrorMessage(), e);
        }

        consumer.registerMessageListener(mqMessageListenerProcessor);

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
        return consumer;
    }


    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    public String getNameServerAddr() {
        return nameServerAddr;
    }

    public void setNameServerAddr(String nameServerAddr) {
        this.nameServerAddr = nameServerAddr;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getTopics() {
        return topics;
    }

    public void setTopics(String topics) {
        this.topics = topics;
    }

    public int getConsumeThreadMin() {
        return consumeThreadMin;
    }

    public void setConsumeThreadMin(int consumeThreadMin) {
        this.consumeThreadMin = consumeThreadMin;
    }

    public int getConsumeThreadMax() {
        return consumeThreadMax;
    }

    public void setConsumeThreadMax(int consumeThreadMax) {
        this.consumeThreadMax = consumeThreadMax;
    }

    public int getConsumeMessageBatchMaxSize() {
        return consumeMessageBatchMaxSize;
    }

    public void setConsumeMessageBatchMaxSize(int consumeMessageBatchMaxSize) {
        this.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize;
    }

    public int getReConsumerTimes() {
        return reConsumerTimes;
    }

    public void setReConsumerTimes(int reConsumerTimes) {
        this.reConsumerTimes = reConsumerTimes;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public boolean isVipChannelEnabled() {
        return vipChannelEnabled;
    }

    public void setVipChannelEnabled(boolean vipChannelEnabled) {
        this.vipChannelEnabled = vipChannelEnabled;
    }
}
