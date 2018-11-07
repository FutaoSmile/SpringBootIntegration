package com.futao.springmvcdemo.service.impl

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
import com.futao.springmvcdemo.model.system.MailM
import com.futao.springmvcdemo.model.system.SystemConfig
import com.futao.springmvcdemo.service.MailService
import com.futao.springmvcdemo.utils.logSms
import org.apache.rocketmq.client.producer.DefaultMQProducer
import org.apache.rocketmq.common.message.Message
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import java.nio.charset.Charset
import javax.annotation.Resource
import kotlin.math.log

/**
 * @author futao
 * Created on 2018/10/17.
 */
@Service
open class MailServiceImpl : MailService {
    private val logger = logSms()

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Resource
    lateinit var sender: JavaMailSender

    @Resource
    lateinit var template: TemplateEngine

    @Resource
    lateinit var producer: DefaultMQProducer

    /**
     * 发送简单邮件
     */
    override fun sendSimpleEmail(to: Array<String>, cc: Array<String>, subject: String, content: String): Boolean {
        return try {
            val mailMessage = SimpleMailMessage().apply {
                from = username
                setTo(*to)
                setCc(*cc)
                setSubject(subject)
                text = content
            }
            logger.info(JSONObject.toJSONString(mailMessage))
            sender.send(mailMessage)
            true
        } catch (e: Exception) {
            logger.error(e.message, e)
            false
        }
    }

    /**
     * 发送html邮件
     */
    override fun sendHtmlEmail(to: Array<String>, cc: Array<String>, subject: String, content: String, isHtml: Boolean): Boolean {
        return try {
            val message = sender.createMimeMessage()
            MimeMessageHelper(message).apply {
                setFrom(username)
                setTo(to)
                setCc(cc)
                setSubject(subject)
                setText(content, isHtml)
            }
            sender.send(message)
            true
        } catch (e: Exception) {
            logger.error(e.message, e)
            false
        }
    }

    /**
     * 使用邮件模板发送邮件
     */
    override fun sendHtmlEmailWithTemplate(to: Array<String>, cc: Array<String>, subject: String, templatePath: String, context: Context): Boolean {
        return try {
            val message = sender.createMimeMessage()
            MimeMessageHelper(message).apply {
                setFrom(username)
                setTo(to)
                setCc(cc)
                setSubject(subject)
                setText(template.process(templatePath, context), true)
            }
            sender.send(message)
            true
        } catch (e: Exception) {
            logger.error(e.message, e)
            false
        }
    }

    /**
     * 通过消息队列发送邮件
     */
    override fun sendMq(mailM: MailM) {
        val message = Message(SystemConfig.ROCKET_MQ_TOPIC_MAIL, SystemConfig.ROCKET_MQ_TAG_MAIL_REGISTER, JSON.toJSONString(mailM).toByteArray(Charset.forName(SystemConfig.UTF8_ENCODE)))
        try {
            producer.send(message)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }
}