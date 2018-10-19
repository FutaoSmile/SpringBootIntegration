package com.futao.springmvcdemo.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author futao
 * Created on 2018/10/18.
 */
@Data
@AllArgsConstructor
public class MqMessage {
    private String messageId;
    private String content;
    private int age;
    private Timestamp createTime;
}
