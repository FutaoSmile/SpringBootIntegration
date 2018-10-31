package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.model.entity.SingleValueResult;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * @author futao
 * Created on ${date}.
 * Server Send Event
 * 服务端发送消息
 */
@RestController
@RequestMapping(path = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public class ServerSendEventController {

    @RequestMapping(path = "push")
//    @Scheduled(fixedDelay = 5)
    public SingleValueResult push() {
        Random r = new Random();
        return new SingleValueResult(r);
    }
}
