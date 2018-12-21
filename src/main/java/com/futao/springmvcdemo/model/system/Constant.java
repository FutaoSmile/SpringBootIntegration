package com.futao.springmvcdemo.model.system;

/**
 * @author futao
 * Created on 2018/9/19-14:50.
 */
public final class Constant {
    /**
     * session中存放登陆用户的id的session key
     */
    public static final String LOGIN_USER_SESSION_KEY = "userLoginUserId";

    /**
     * UTF-8编码
     */
    public static final String UTF8_ENCODE = "UTF-8";

    /**
     * rocket mq 发送邮件的 topic
     */
    public static final String ROCKET_MQ_TOPIC_MAIL = "topic_mail";

    /**
     * rocket mq 发送邮件-注册邮件的tag
     */
    public static final String ROCKET_MQ_TAG_MAIL_REGISTER = "tag_mail_register";
    /**
     * Service事物超时时间
     */
    public static final int SERVICE_TIMEOUT_TIME = 2;

}
