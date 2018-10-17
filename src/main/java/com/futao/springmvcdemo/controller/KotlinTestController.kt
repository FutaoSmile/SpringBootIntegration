package com.futao.springmvcdemo.controller

import com.futao.springmvcdemo.model.entity.User
import com.futao.springmvcdemo.service.MailService
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
@RestController
@RequestMapping(path = ["kotlinTest"], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
open class KotlinTestController {

    @Resource
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    @Resource
    private lateinit var mailService: MailService

    /**
     * 存入缓存
     */
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
    @GetMapping(path = ["getCache"])
    open fun getCache(
            @RequestParam("name") name: String
    ): User? {
        return if (redisTemplate.opsForValue().get(name) != null) {
            redisTemplate.opsForValue().get(name) as User
        } else null
    }

    @GetMapping(path = ["mailSend"])
    open fun mailSend() {
        mailService.sendSimpleEmail(arrayOf("taof@wicrenet.com", "1185172056@qq.com"), arrayOf("taof@wicrenet.com", "1185172056@qq.com"), "SpringbootMail", "from Springboot SimpleMail")
    }

    @GetMapping(path = ["htmlMailSend"])
    open fun htmlMailSend(
            @RequestParam("isHtml") isHtml: Boolean,
            @RequestParam("content") content: String
    ) {
        mailService.sendHtmlEmail(arrayOf("taof@wicrenet.com", "1185172056@qq.com"), arrayOf("taof@wicrenet.com", "1185172056@qq.com"), "SpringbootMail", content, isHtml)
    }

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
}