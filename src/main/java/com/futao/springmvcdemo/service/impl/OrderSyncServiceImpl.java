/*
 * Copyright (c) 2018.
 */

package com.futao.springmvcdemo.service.impl;

import com.futao.springmvcdemo.dao.OrderDao;
import com.futao.springmvcdemo.model.entity.Order;
import com.futao.springmvcdemo.service.OrderSyncService;
import com.futao.springmvcdemo.service.UUIDService;
import com.futao.springmvcdemo.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

import static com.futao.springmvcdemo.utils.TimeUtilsKt.currentTimeStamp;

/**
 * @author futao
 * Created on 2018/11/20.
 */
@Service
public class OrderSyncServiceImpl implements OrderSyncService {

    @Resource
    private OrderDao orderDao;

    @Resource
    private UserService userService;

    /**
     * 根据erpOrderId查询订单是否存在
     *
     * @param erpOrderId
     * @return
     */
    @Override
    public Boolean queryIfExistByErpOrderId(String erpOrderId) {
        Order order = orderDao.queryIfExistByErpOrderId(erpOrderId);
        return order != null;
    }

    /**
     * 新增订单
     *
     * @param erpOrderId 进销存订单id
     * @return
     */
    @Override
    public int add(String erpOrderId) {
        Timestamp currentTimeStamp = currentTimeStamp();
        return orderDao.add(UUIDService.get(), userService.currentUser().getId(), erpOrderId, currentTimeStamp, currentTimeStamp);
    }

    /**
     * 直接通过sql新增订单
     *
     * @param erpOrderId
     * @return
     */
    public int addOrder(String erpOrderId) {
        Timestamp currentTimeStamp = currentTimeStamp();
        return orderDao.addOrder(UUIDService.get(), userService.currentUser().getId(), erpOrderId, currentTimeStamp, currentTimeStamp);
    }


    @Override
    public String sync(int times) {
//        long startTime1 = System.currentTimeMillis();
//        for (int i = 0; i < times; i++) {
//            String uuid = UUIDService.get();
//            if (!queryIfExistByErpOrderId(uuid)) {
//                add(uuid);
//            }
//        }
//        long time1 = System.currentTimeMillis() - startTime1;

        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            addOrder(UUIDService.get());
        }

        long time2 = System.currentTimeMillis() - startTime2;


//        return "先查询再新增耗时：" + time1;
        return "===直接新增耗时：" + time2;
    }


    @Override
    public void addOrder2(String erpOrderId, String remark) {
        Timestamp currentTimeStamp = currentTimeStamp();

//        orderDao.addOrUpdateByDuplicateKey(UUIDService.get(), userService.currentUser().getId(), erpOrderId, remark, currentTimeStamp, currentTimeStamp);

        orderDao.addOrUpdateByReplace(UUIDService.get(), userService.currentUser().getId(), erpOrderId, remark, currentTimeStamp, currentTimeStamp);
    }
}
