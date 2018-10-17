package com.futao.springmvcdemo.service

import org.thymeleaf.context.Context

interface MailService {

    fun sendSimpleEmail(to: Array<String>, cc: Array<String>, subject: String, content: String)
    fun sendHtmlEmail(to: Array<String>, cc: Array<String>, subject: String, content: String, isHtml: Boolean)
    fun sendHtmlEmailWithTemplate(to: Array<String>, cc: Array<String>, subject: String, templatePath: String, context: Context)
}
