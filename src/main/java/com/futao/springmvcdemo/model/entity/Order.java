/*
 * Copyright (c) 2018.
 */

package com.futao.springmvcdemo.model.entity;

import lombok.*;

/**
 * @author futao
 * Created on 2018/11/20.
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseEntity {
    /**
     * 第三方进销存订单编号
     */
    private String erpOrderId;
    /**
     * 订单所属用户id
     */
    private String userId;
}
