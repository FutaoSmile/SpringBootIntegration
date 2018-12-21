package com.futao.springmvcdemo.service

import org.thymeleaf.context.Context

interface MailService {

    fun sendHtmlEmailWithTemplate(to: Array<String>, cc: Array<String>, subject: String, templatePath: String, context: Context): Boolean
    fun sendHtmlEmail(to: Array<String>, cc: Array<String>, subject: String, content: String, isHtml: Boolean): Boolean
    fun sendSimpleEmail(to: Array<String>, cc: Array<String>, subject: String, content: String): Boolean
//    fun sendMq(mailM: MailM)
//    fun sendMq(mailM: MailmSingle)
}
