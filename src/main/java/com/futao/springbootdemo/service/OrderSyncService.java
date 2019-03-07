/*
 * Copyright (c) 2018.
 */

package com.futao.springbootdemo.service;

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

    /**
     * 订单同步
     *
     * @param times
     * @return
     */
    String sync(int times);

    /**
     * 新增订单
     *
     * @param erpOrderId
     * @param remark
     */
    void addOrder2(String erpOrderId, String remark);
}
