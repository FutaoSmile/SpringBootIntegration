package com.futao.springbootdemo.foundation.configuration.mq.rocket;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * Rocket Mq 生产者
 *
 * @author futao
 * Created on 2018-12-27.
 */
@Configuration
@ConfigurationProperties(prefix = "rocketmq.producer")
public class RocketMqProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RocketMqProducer.class);

    /**
     * 启动or停用
     */
    private String onOff;

    /**
     * 组名
     */
    private String groupName;

    /**
     * 服务注册地址
     */
    private String nameServerAddr;

    /**
     * 单条消息最大数据量
     */
    private int maxMessageSize;

    /**
     * 发送消息超时时间
     */
    private int sendMsgTimeout;

    /**
     * 发送失败重试次数
     */
    private int retryTimesWhenSendFailed;

    /**
     * 定期发送注册心跳到broker的间隔
     */
    private int heartbeatBrokerInterval;

    /**
     * 是否启用vip netty通道以发送消息
     * <p>
     * broker的netty server会起两个通信服务
     * 两个服务除了服务的端口号不一样，其他都一样
     * 其中一个的端口（配置端口-2）作为vip通道，客户端可以启用本设置项把发送消息此vip通道。
     */
    private boolean vipChannelEnabled;

    /**
     * @return DefaultMQProducer-Bean
     */
    @Bean
    @Conditional(RocketMqProducerOnOff.class)
    public DefaultMQProducer producer() {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setNamesrvAddr(nameServerAddr);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setProducerGroup(groupName);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        producer.setVipChannelEnabled(vipChannelEnabled);
        producer.setHeartbeatBrokerInterval(heartbeatBrokerInterval);
        try {
            //start()只能被执行一次，且必须在发送消息和查询消息之前
            producer.start();
            LOGGER.info("\n\nrocketMq Producer start success; \nnameServerAddr:【{}】,producerGroupName:【{}】\n", nameServerAddr, groupName);
        } catch (MQClientException e) {
            e.printStackTrace();
            LOGGER.error("rocketMq Producer start fail;\nmessage:{}\nerrorMessage:{}\n", e.getMessage(), e.getErrorMessage(), e);
        }
        return producer;
    }


    public String getOnOff() {
        return onOff;
    }

    public void setOnOff(String onOff) {
        this.onOff = onOff;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getNameServerAddr() {
        return nameServerAddr;
    }

    public void setNameServerAddr(String nameServerAddr) {
        this.nameServerAddr = nameServerAddr;
    }

    public int getMaxMessageSize() {
        return maxMessageSize;
    }

    public void setMaxMessageSize(int maxMessageSize) {
        this.maxMessageSize = maxMessageSize;
    }

    public int getSendMsgTimeout() {
        return sendMsgTimeout;
    }

    public void setSendMsgTimeout(int sendMsgTimeout) {
        this.sendMsgTimeout = sendMsgTimeout;
    }

    public int getRetryTimesWhenSendFailed() {
        return retryTimesWhenSendFailed;
    }

    public void setRetryTimesWhenSendFailed(int retryTimesWhenSendFailed) {
        this.retryTimesWhenSendFailed = retryTimesWhenSendFailed;
    }

    public int getHeartbeatBrokerInterval() {
        return heartbeatBrokerInterval;
    }

    public void setHeartbeatBrokerInterval(int heartbeatBrokerInterval) {
        this.heartbeatBrokerInterval = heartbeatBrokerInterval;
    }

    public boolean isVipChannelEnabled() {
        return vipChannelEnabled;
    }

    public void setVipChannelEnabled(boolean vipChannelEnabled) {
        this.vipChannelEnabled = vipChannelEnabled;
    }


}
