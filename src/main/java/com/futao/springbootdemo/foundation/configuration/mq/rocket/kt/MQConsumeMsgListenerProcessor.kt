package com.futao.springbootdemo.foundation.configuration.mq.rocket.kt

import com.futao.springbootdemo.model.system.Constant
import org.apache.commons.lang3.StringUtils
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently
import org.apache.rocketmq.common.message.MessageExt
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.nio.charset.Charset

/**
 * RocketMq消费者消息监听处理器
 */
@Service
open class MQConsumeMsgListenerProcessor : MessageListenerConcurrently {
    private val logger = LoggerFactory.getLogger(MQConsumeMsgListenerProcessor::class.java)
    @Value("\${rocketmq.consumer.topics}")
    private lateinit var topic: String

    @Value("\${rocketmq.consumer.tags}")
    private lateinit var tag: String

    @Value("\${rocketmq.consumer.reConsumerTimes}")
    private var reComsumerTimes: Int = 0

    /**
     * 默认msgs里只有一条消息，可以通过设置consumeMessageBatchMaxSize参数来批量接收消息<br/>
     * 不要抛异常，如果没有return CONSUME_SUCCESS ，consumer会重新消费该消息，直到return CONSUME_SUCCESS
     *
     * It is not recommend to throw exception,rather than returning ConsumeConcurrentlyStatus.RECONSUME_LATER if consumption failure
     *
     * @param msgs
     * msgs.size() >= 1<br></br>
     * DefaultMQPushConsumer.consumeMessageBatchMaxSize=1，you can modify here
     * @param context
     *
     *
     *
     * @return
     */
    override fun consumeMessage(msgs: MutableList<MessageExt>?, context: ConsumeConcurrentlyContext?): ConsumeConcurrentlyStatus {
        if (msgs == null || msgs.isEmpty()) {
            logger.info("接受到的消息为空，不处理，直接返回成功")
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS
        }
        val msg = msgs[0]
        logger.info("接收到的消息为：" + msg.toString())
        if (msg.topic == topic && msg.tags == tag) {
            //判断该消息是否重复消费（RocketMQ不保证消息不重复，如果你的业务需要保证严格的不重复消息，需要你自己在业务端去重）
            //获取该消息重试次数
            if (msg.reconsumeTimes >= reComsumerTimes) {
                //消息已经重试了3次，如果不需要再次消费，则返回成功
                //TODO("如果重试了三次还是失败则执行对于失败的业务逻辑")
                logger.error("消息重试消费失败：", msg)
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS
            } else {
                //如果失败重试次数还没到三次则继续重试
                ConsumeConcurrentlyStatus.RECONSUME_LATER
            }
            //TODO("开始正常的业务逻辑")
            println(StringUtils.repeat(":", 30) + String(msg.body, Charset.forName(Constant.UTF8_ENCODE)))
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS    //消费成功
    }


}
