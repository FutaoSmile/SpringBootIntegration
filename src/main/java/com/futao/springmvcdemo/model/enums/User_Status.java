package com.futao.springmvcdemo.model.enums;

/**
 * 用户状态
 *
 * @author futao
 * Created on 2018-12-11.
 */
public enum User_Status {
    /**
     * 0=预注册
     */
    Pre_Register(0, "预注册"),
    /**
     * 1=正常
     */
    Normal(1, "正常");

    /**
     * 状态码
     */
    private int code;
    /**
     * 描述
     */
    private String description;

    User_Status(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }}
