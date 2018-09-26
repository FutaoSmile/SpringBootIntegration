package com.futao.springmvcdemo.service.impl;

import com.futao.springmvcdemo.service.MailSender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2018/9/23-11:21.
 */
@Service
public class MailSenderImpl implements MailSender {

    @Value("spring.mail.username")
    private String from;
    @Resource
    private JavaMailSender mailSender;

    /**
     * 发送邮件
     *
     * @param to
     * @param cc
     * @param subject
     * @param content
     */
    @Override
    public void send(String[] to, String[] cc, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(from);
        simpleMailMessage.setTo(to);
        simpleMailMessage.setCc(cc);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);
        mailSender.send(simpleMailMessage);
    }
}
