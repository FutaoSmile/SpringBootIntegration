package com.futao.springbootdemo.controller

import com.futao.springbootdemo.foundation.configuration.mq.rocket.RocketMqProducerOnOff
import com.futao.springbootdemo.model.entity.SingleValueResult
import com.futao.springbootdemo.model.entity.User
import com.futao.springbootdemo.service.KotlinTestService
import com.futao.springbootdemo.service.MailService
import com.futao.springbootdemo.service.impl.AccessLimitServiceImpl
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Conditional
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.thymeleaf.context.Context
import javax.annotation.Resource

/**
 * @author futao
 * Created on 2018/10/17.
 */
@Conditional(RocketMqProducerOnOff::class)
@RestController
@RequestMapping(path = ["kotlinTest"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
//@ApiIgnore
@Api("Kotlin测试")
open class KotlinTestController {

    @Resource
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    @Resource
    private lateinit var mailService: MailService

    /**
     * 存入缓存
     */
    @ApiOperation("缓存set测试")
    @GetMapping(path = ["setCache"])
    open fun cache(
            @RequestParam("name") name: String,
            @RequestParam("age") age: Int
    ): User {
        val user = User().apply {
            username = name
            setAge(age.toString())
        }
        redisTemplate.opsForValue().set(name, user)
        return user
    }


    /**
     * 获取缓存
     */
    @ApiOperation("缓存get测试")
    @GetMapping(path = ["getCache"])
    open fun getCache(
            @RequestParam("name") name: String
    ): User? {
        return if (redisTemplate.opsForValue().get(name) != null) {
            redisTemplate.opsForValue().get(name) as User
        } else null
    }

    /**
     * 发送普通邮件测试
     */
    @ApiOperation("发送普通邮件测试")
    @GetMapping(path = ["mailSend"])
    open fun mailSend() {
        mailService.sendSimpleEmail(arrayOf("taof@wicrenet.com", "1185172056@qq.com"), arrayOf("taof@wicrenet.com", "1185172056@qq.com"), "SpringbootMail", "from Springboot SimpleMail")
    }

    /**
     * 发送html邮件测试
     */
    @ApiOperation("发送html邮件测试")
    @GetMapping(path = ["htmlMailSend"])
    open fun htmlMailSend(
            @RequestParam("isHtml") isHtml: Boolean,
            @RequestParam("content") content: String
    ) {
        mailService.sendHtmlEmail(arrayOf("taof@wicrenet.com", "1185172056@qq.com"), arrayOf("taof@wicrenet.com", "1185172056@qq.com"), "SpringbootMail", content, isHtml)
    }


    /**
     * 发送html模板邮件测试
     */
    @ApiOperation("发送html模板邮件测试")
    @GetMapping(path = ["htmlTemplateHtml"])
    open fun htmlTemplateHtml(
            @RequestParam("templatePath") templatePath: String
    ) {
        mailService.sendHtmlEmailWithTemplate(
                arrayOf("taof@wicrenet.com", "1185172056@qq.com"),
                arrayOf("taof@wicrenet.com", "1185172056@qq.com"),
                "SpringbootMail",
                templatePath,
                Context().apply {
                    setVariable("username", "futao")
                }
        )
    }

    /**
     * mq
     */
    @ApiOperation("消息队列")
    @GetMapping("producer")
    open fun producer(
            @RequestParam("message") message: String,
            @RequestParam("repeat") repeat: Int
    ) {
//        for (i in (1..repeat)) {
//            val message1 = Message(Constant.ROCKET_MQ_TOPIC_MAIL, Constant.ROCKET_MQ_TAG_MAIL_REGISTER, (message + i).toByteArray(Charset.forName(Constant.UTF8_ENCODE)))
//            logger.info("开始发送消息：${message + i},::,$message1")
//            val sendResult = rocketMqService.send(message1)
//            logger.info("发送消息结果：$sendResult")
//        }
    }


    /**
     * 消息队列发送邮件
     */
    @GetMapping("sendMailMq")
    @ApiOperation("消息队列发送邮件")
    open fun sendMailMq() {
//        MailM().apply {
//            to = arrayOf("1185172056@qq.com")
//            cc = arrayOf("taof@wicrenet.com")
//            subject = "消息队列"
//            content = "<h1>您好，RocketMq</h1>"
//        }
//        mailService.sendHtmlEmail(mailM)
    }

    @Resource
    private lateinit var accessLimitServiceImpl: AccessLimitServiceImpl

    /**
     * 接口限流测试-令牌桶
     */
    @RequestMapping("rate")
    @ApiOperation("接口限流测试-令牌桶")
    open fun rateLimit(): SingleValueResult {
        return if (!accessLimitServiceImpl.getPermit()) {
            SingleValueResult("限流")
        } else {
            SingleValueResult("正常业务逻辑")
        }
    }


    @Resource
    private lateinit var ktService: KotlinTestService

    /**
     * 映射多路径测试
     */
    @ApiOperation("映射多路径测试")
    @GetMapping(path = ["os", "111", "222"])
    open fun os(): SingleValueResult {
        return SingleValueResult(ktService.t())
    }

    @GetMapping("mq")
    open fun mq() {
        ktService.send()
    }
}