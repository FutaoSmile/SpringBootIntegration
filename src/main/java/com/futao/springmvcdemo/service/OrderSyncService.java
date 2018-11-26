/*
 * Copyright (c) 2018.
 */

package com.futao.springmvcdemo.service;

/**
 * @author futao
 * Created on 2018/11/20.
 */
public interface OrderSyncService {
    /**
     * 根据erpOrderId查询订单是否存在
     *
     * @param erpOrderId
     * @return
     */
    Boolean queryIfExistByErpOrderId(String erpOrderId);

    /**
     * 新增订单
     *
     * @param erpOrderId 进销存订单id
     * @return
     */
    int add(String erpOrderId);

    String sync(int times);

    void addOrder2(String erpOrderId, String remark);
}
