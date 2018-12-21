package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.model.entity.SingleValueResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Random;

/**
 * @author futao
 * Created on ${date}.
 * Server Send Event
 * 服务端发送消息
 */
@RestController
@RequestMapping(path = "sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
@ApiIgnore
@Api("服务端发送消息")
public class ServerSendEventController {


    /**
     * 服务端发送消息
     *
     * @return
     */
    @ApiOperation("服务端发送消息")
    @RequestMapping(path = "push")
//    @Scheduled(fixedDelay = 5)
    public SingleValueResult push() {
        Random r = new Random();
        return new SingleValueResult(r);
    }
}
