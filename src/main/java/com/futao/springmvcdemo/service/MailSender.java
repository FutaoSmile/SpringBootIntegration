package com.futao.springmvcdemo.service;

/**
 * @author futao
 * Created on 2018/9/23-11:21.
 */
public interface MailSender {
    void send(String[] to, String[] cc, String subject, String content);
}
