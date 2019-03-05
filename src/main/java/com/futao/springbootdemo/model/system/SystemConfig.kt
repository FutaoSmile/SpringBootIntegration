package com.futao.springbootdemo.model.system

/**
 * @author futao
 * Created on ${date}.
 */

/**
 * session中存放登陆用户的id的session key
 */
const val LOGIN_USER_SESSION_KEY = "userLoginUserId"
/**
 * session过期时间,单位：秒
 */
const val SESSION_INVALIDATE_SECOND = 10

/**
 * UTF-8编码
 */
const val UTF8_ENCODE = "UTF-8"

/**
 * rocket mq 发送邮件的 topic
 */
const val ROCKET_MQ_TOPIC_MAIL = "topic_mail"

/**
 * rocket mq 发送邮件-注册邮件的tag
 */
const val ROCKET_MQ_TAG_MAIL_REGISTER = "tag_mail_register"
/**
 * Service事物超时时间
 */
const val SERVICE_TIMEOUT_TIME = 2