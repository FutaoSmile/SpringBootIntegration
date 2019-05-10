package com.futao.springbootdemo.foundation.mq.rabbit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.foundation.configuration.HttpMessageConverterConfiguration;
import com.futao.springbootdemo.model.system.RedisKeySet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 消息投递成功ack确认与消息发送失败处理
 *
 * @author futao
 * Created on 2019-05-10.
 */
@Slf4j
@Component
public class RabbitMqProducerConfirmAndReturnHandler implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {


    private final RabbitTemplate rabbitTemplate;

    private final RedisTemplate<String, String> redisTemplate;

    /**
     * Spring更推荐使用构造方法注入
     * https://blog.csdn.net/qq_15037231/article/details/82841115
     *
     * @param rabbitTemplate
     */
    @Autowired
    public RabbitMqProducerConfirmAndReturnHandler(RabbitTemplate rabbitTemplate, RedisTemplate<String, String> redisTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    public void init() {
        //消息投递确认回调ack
        rabbitTemplate.setConfirmCallback(this);
        //消息发送失败处理
        rabbitTemplate.setReturnCallback(this);
    }

    /**
     * 发送确认回调
     *
     * @param correlationData 消息的唯一标识
     * @param ack             发送结果
     * @param cause           失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("消息唯一标识[{}]", correlationData);
        if (ack) {
            log.info("确认结结果:[{}]", "成功");
        } else {
            log.info("确认结结果:[{}]", "失败");
            log.error("失败原因:[{}]", cause);
        }
    }


    /**
     * 启动消息失败返回，比如路由不到队列时触发回调
     *
     * @param message    消息
     * @param replyCode
     * @param replyText  描述
     * @param exchange   交换器
     * @param routingKey 路由键
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        JSONObject result = new JSONObject();
        result.put("message", message);
        result.put("replyCode", replyCode);
        result.put("replyText", replyText);
        result.put("exchange", exchange);
        result.put("routingKey", routingKey);
        redisTemplate.opsForList().leftPush(RedisKeySet.gen(RedisKeySet.RABBITMQ_SEND_FAIL, routingKey), JSON.toJSONString(result, HttpMessageConverterConfiguration.SERIALIZER_FEATURES));
        log.error("消息发送失败");
        log.error("消息主体 message : {}", message);
        log.error("错误码 : {}", replyCode);
        log.error("描述：{}", replyText);
        log.error("消息使用的交换器 exchange : {}", exchange);
        log.error("消息使用的路由键 routingKey : {}", routingKey);
    }

}
