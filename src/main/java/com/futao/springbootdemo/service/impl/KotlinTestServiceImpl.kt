package com.futao.springbootdemo.service.impl

import com.futao.springbootdemo.foundation.mq.rocket.RocketMqProducerOnOff
import com.futao.springbootdemo.model.system.ErrorMessage
import com.futao.springbootdemo.service.KotlinTestService
import com.lazyer.foundation.foundation.exception.LogicException
import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.apache.rocketmq.common.message.Message
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Conditional
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**

 * 实现Condition接口，重写matches()方法，不需要打@Service注解，否则matches无效
 * matches()返回为true时Spring会实例化这个类
 * 使用：
 *  可以在其他类上打上@Condition(K::class)注解，会根据K类是否被实例化再来实例化自己
 *
 *   @author futao
 */

@Conditional(RocketMqProducerOnOff::class)
@Service
open class KotlinTestServiceImpl : KotlinTestService {

    val logger = LoggerFactory.getLogger(KotlinTestServiceImpl::class.java)!!

    override fun t(): String {
        return "test"
    }


    override fun exception() {
        throw LogicException.le(ErrorMessage.LogicErrorMessage.LOGIC_EXCEPTION)
    }

    @Resource
    lateinit var producer: DefaultMQProducer

    override fun send() {
        producer.send(Message("niubiTopic", "niubiTag", "我是消息本体了".toByteArray()))
    }
}