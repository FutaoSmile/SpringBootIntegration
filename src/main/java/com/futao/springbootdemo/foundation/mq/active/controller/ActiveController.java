package com.futao.springbootdemo.foundation.mq.active.controller;

import com.futao.springbootdemo.foundation.mq.active.ptp.PtpProducer;
import com.futao.springbootdemo.foundation.mq.active.topic.ActiveMqProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-06-04.
 */
@RequestMapping("/activemq")
@RestController
public class ActiveController {


    @Resource
    private ActiveMqProducer activeMqProducer;

    @Resource
    private PtpProducer ptpProducer;

    @PostMapping("/ptp/sender")
    public void ptpSender(@RequestParam String msg) {
        ptpProducer.send(msg);
    }


    @PostMapping("/ps/sender")
    public void pushTest(@RequestParam String msg) {
        activeMqProducer.send(msg);
    }
}
