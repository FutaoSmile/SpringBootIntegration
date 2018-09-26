package com.futao.springmvcdemo;

import com.futao.springmvcdemo.service.MailSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringmvcdemoApplicationTests {

    @Resource
    private com.futao.springmvcdemo.service.MailSender mailSender;

    @Test
    public void test6() {
        String[] to = new String[]{"1185172056@qq.com", "taof@wicrenet.com"};
        String[] cc = new String[]{"1185172056@qq.com", "taof@wicrenet.com"};
        mailSender.send(to, cc, "星弟", "星弟");
    }

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private JavaMailSender javaMailSender;

    @Test
    public void test5() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("taof@wicrenet.com");
        simpleMailMessage.setSubject("你好");
        simpleMailMessage.setText("我很好，你呢");
        simpleMailMessage.setFrom(from);
        javaMailSender.send(simpleMailMessage);
    }

}
