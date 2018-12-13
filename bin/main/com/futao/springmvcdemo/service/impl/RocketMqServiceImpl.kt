package com.futao.springmvcdemo.service.impl

import com.futao.springmvcdemo.foundation.LogicException
import com.futao.springmvcdemo.model.system.ErrorMessage
import com.futao.springmvcdemo.model.system.Constant
import com.futao.springmvcdemo.service.RocketMqService
import org.apache.commons.lang3.StringUtils
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently
import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.apache.rocketmq.common.consumer.ConsumeFromWhere
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.nio.charset.Charset


/**
 * @author futao
 * Created on 2018/10/18.
 */
@Service
open class RocketMqServiceImpl : RocketMqService {

    private val logger = LoggerFactory.getLogger(RocketMqServiceImpl::class.java)

    /**
     * 发送同一类消息的设置为同一个group，保证唯一,默认不需要设置，rocketmq会使用ip@pid(pid代表jvm名字)作为唯一标示
     */
    @Value("\${rocketmq.producer.groupName}")
    private lateinit var producerGroupName: String

    @Value("\${rocketmq.producer.namesrvAddr}")
    private lateinit var producerNamesrvAddr: String
    /**
     * 消息最大大小，默认4M
     */
    @Value("\${rocketmq.producer.maxMessageSize}")
    private var maxMessageSize: Int = 0
    /**
     * 消息发送超时时间，默认3秒
     */
    @Value("\${rocketmq.producer.sendMsgTimeout}")
    private var sendMsgTimeout: Int = 0
    /**
     * 消息发送失败重试次数，默认2次
     */
    @Value("\${rocketmq.producer.retryTimesWhenSendFailed}")
    private var retryTimesWhenSendFailed: Int = 0


    /**
     * 生产者Bean
     */
    @Bean
    override fun producer(): DefaultMQProducer {
        if (this.producerGroupName.isEmpty()) {
            throw LogicException.le(ErrorMessage.ROCKET_MQ_PRODUCER_GROUP_NAME_EMPTY)
        }
        if (this.producerNamesrvAddr.isEmpty()) {
            throw LogicException.le(ErrorMessage.ROCKET_MQ_PRODUCER_NAME_SERVER_EMPTY)
        }
        val defaultMQProducer = DefaultMQProducer(producerGroupName)
        defaultMQProducer.namesrvAddr = producerNamesrvAddr
        defaultMQProducer.maxMessageSize = maxMessageSize
        defaultMQProducer.sendMsgTimeout = sendMsgTimeout
        defaultMQProducer.isVipChannelEnabled = false
        //消息发送到mq服务器失败重试次数
        defaultMQProducer.retryTimesWhenSendFailed = retryTimesWhenSendFailed
        try {
            defaultMQProducer.start()
            logger.info("rocketMq Producer start success; nameServer:{},producerGroupName:{}", producerNamesrvAddr, producerGroupName)
        } catch (e: Exception) {
            logger.error("rocketMq Producer start fail;{}", e.message, e)
        }
        return defaultMQProducer
    }


    @Value("\${rocketmq.consumer.namesrvAddr}")
    private lateinit var consumerNamesrvAddr: String

    @Value("\${rocketmq.consumer.groupName}")
    private lateinit var consumerGroupName: String

    @Value("\${rocketmq.consumer.consumeThreadMin}")
    private var consumeThreadMin: Int = 0

    @Value("\${rocketmq.consumer.consumeThreadMax}")
    private var consumeThreadMax: Int = 0

    @Value("\${rocketmq.consumer.topics}")
    private lateinit var topics: String

    @Value("\${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private var consumeMessageBatchMaxSize: Int = 0

//    @Resource
//    private lateinit var mqMessageListenerProcessor: MQConsumeMsgListenerProcessor


    @Value("\${rocketmq.consumer.reConsumerTimes}")
    private var reConsumerTimes: Int = 0


    /**
     * 消费者Bean
     */
    @Bean
    override fun consumer(): DefaultMQPushConsumer {
        val topic = Constant.ROCKET_MQ_TOPIC_MAIL
        val tag = Constant.ROCKET_MQ_TAG_MAIL_REGISTER
        if (this.consumerGroupName.isEmpty()) {
            throw LogicException.le(ErrorMessage.ROCKET_MQ_CONSUMER_GROUP_NAME_EMPTY)
        }
        if (this.consumerNamesrvAddr.isEmpty()) {
            throw LogicException.le(ErrorMessage.ROCKET_MQ_PRODUCER_NAME_SERVER_EMPTY)
        }
        if (this.topics.isEmpty()) {
            throw LogicException.le(ErrorMessage.ROCKET_MQ_CONSUMER_TOPICS_EMPTY)
        }
        try {
            //DefaultMQPushConsumer DefaultMQPullConsumer
            val defaultMQPushConsumer = DefaultMQPushConsumer(consumerGroupName)
            defaultMQPushConsumer.namesrvAddr = consumerNamesrvAddr
            defaultMQPushConsumer.consumeThreadMin = consumeThreadMin
            defaultMQPushConsumer.isVipChannelEnabled = false
//        defaultMQPushConsumer.createTopic()
            defaultMQPushConsumer.consumeThreadMax = consumeThreadMax
            //消费模式 集群还是广播，默认为集群(自动负载均衡)
            //广播消费： 消息会发给Consume Group中的每一个消费者进行消费,如果设置为广播消息会导致NOT_ONLINE异常,https://github.com/apache/rocketmq/issues/296
            defaultMQPushConsumer.messageModel = MessageModel.CLUSTERING
            // 设置消费模型，
            //consumer.setMessageModel(MessageModel.CLUSTERING);

            // * 设置Consumer第一次启动是从队列头部开始消费还是队列尾部开始消费
            // * 如果非第一次启动，那么按照上次消费的位置继续消费
            defaultMQPushConsumer.consumeFromWhere = ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET
            //设置一次消费消息的条数，默认为1条
            defaultMQPushConsumer.consumeMessageBatchMaxSize = consumeMessageBatchMaxSize
            //订阅topic
            defaultMQPushConsumer.subscribe(topic, tag)

            //        defaultMQPushConsumer.registerMessageListener(mqMessageListenerProcessor)
            defaultMQPushConsumer.registerMessageListener(
                    MessageListenerConcurrently { msgs, _ ->
                        if (msgs == null || msgs.isEmpty()) {
                            logger.info("接受到的消息为空，不处理，直接返回成功")
                            return@MessageListenerConcurrently ConsumeConcurrentlyStatus.CONSUME_SUCCESS
                        }
                        val msg = msgs[0]
                        logger.info("接收到的消息为：" + msg.toString())
                        if (msg.topic == topic && msg.tags == tag) {
                            //判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
                            //获取该消息重试次数
                            if (msg.reconsumeTimes >= reConsumerTimes) {
                                //消息已经重试了3次，如果不需要再次消费，则返回成功
                                //TODO("如果重试了三次还是失败则执行对于失败的业务逻辑")
                                logger.error("消息重试消费失败：", msg)
                                return@MessageListenerConcurrently ConsumeConcurrentlyStatus.CONSUME_SUCCESS
                            } else {
                                //如果失败重试次数还没到三次则继续重试
                                ConsumeConcurrentlyStatus.RECONSUME_LATER
                            }
                            //TODO("开始正常的业务逻辑")
                            println(StringUtils.repeat(":", 30) + String(msg.body, Charset.forName(Constant.UTF8_ENCODE)))
                        }
                        return@MessageListenerConcurrently ConsumeConcurrentlyStatus.CONSUME_SUCCESS    //消费成功
                    }
            )
            defaultMQPushConsumer.start()
            logger.info("rocketMq Consumer start success; namesrvAddr:{},groupName:{},topics:{}", consumerNamesrvAddr, consumerGroupName, topics)
            return defaultMQPushConsumer
        } catch (e: Exception) {
            logger.error("rocketMq Consumer start fail;{}", e.message, e)
            return DefaultMQPushConsumer()
        }
    }
}