package com.futao.springmvcdemo.service.impl

import com.futao.springmvcdemo.service.MailService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import javax.annotation.Resource

/**
 * @author futao
 * Created on 2018/10/17.
 */
@Service
open class MailServiceImpl : MailService {
    private val logger = LoggerFactory.getLogger(MailServiceImpl::class.java)

    @Value("\${spring.mail.username}")
    lateinit var username: String

    @Resource
    lateinit var sender: JavaMailSender

    @Resource
    lateinit var template: TemplateEngine

    /**
     * 发送简单邮件
     */
    override fun sendSimpleEmail(to: Array<String>, cc: Array<String>, subject: String, content: String) {
        try {
            val mailMessage = SimpleMailMessage().apply {
                from = username
                setTo(*to)
                setCc(*cc)
                setSubject(subject)
                text = content
            }
            sender.send(mailMessage)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

    /**
     * 发送html邮件
     */
    override fun sendHtmlEmail(to: Array<String>, cc: Array<String>, subject: String, content: String, isHtml: Boolean) {
        try {
            val message = sender.createMimeMessage()
            MimeMessageHelper(message).apply {
                setFrom(username)
                setTo(to)
                setCc(cc)
                setSubject(subject)
                setText(content, isHtml)
            }
            sender.send(message)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }

    /**
     * 使用邮件模板发送邮件
     */
    override fun sendHtmlEmailWithTemplate(to: Array<String>, cc: Array<String>, subject: String, templatePath: String, context: Context) {
        try {
            val message = sender.createMimeMessage()
            MimeMessageHelper(message).apply {
                setFrom(username)
                setTo(to)
                setCc(cc)
                setSubject(subject)
                setText(template.process(templatePath, context), true)
            }
            sender.send(message)
        } catch (e: Exception) {
            logger.error(e.message, e)
        }
    }
}