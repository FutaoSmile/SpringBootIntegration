/*
 * Copyright (c) 2018.
 */

package com.futao.springmvcdemo.controller.business;

import com.futao.springmvcdemo.annotation.LoginUser;
import com.futao.springmvcdemo.model.entity.SingleValueResult;
import com.futao.springmvcdemo.service.OrderSyncService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2018/11/20.
 */
@RestController
@RequestMapping(path = "order", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@ApiIgnore
@Api("订单")
public class OrderController {

    @Resource
    private OrderSyncService orderSyncService;

    /**
     * 同步第三方订单
     *
     * @param times 次数
     * @return
     */
    @ApiOperation("同步第三方订单")
    @LoginUser
    @PostMapping(path = "sync")
    public SingleValueResult sync(@RequestParam("times") int times) {
        return new SingleValueResult(orderSyncService.sync(times));
    }


    @ApiOperation("添加")
    @LoginUser
    @PostMapping("add")
    public SingleValueResult add(

            @RequestParam("erpOrderId") String erpOrderId,
            @RequestParam("remark") String remark
    ) {
        orderSyncService.addOrder2(erpOrderId, remark);
        return new SingleValueResult("success");
    }
}
