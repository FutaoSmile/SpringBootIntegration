package com.futao.springmvcdemo.model.enums;

import com.futao.springmvcdemo.model.enums.face.IEnum;

/**
 * @author futao
 * Created on 2018-12-11.
 * <p>
 * 用户性别
 */
public enum UserSexEnum implements IEnum {
    /**
     * 0=未知
     */
    UNKNOWN(0, "未知"),
    /**
     * 1=男
     */
    MEN(1, "男"),
    /**
     * 2=女
     */
    WOMEN(2, "女");

    private int code;
    private String description;

    UserSexEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String getStatus() {
        return String.valueOf(this.code);
    }}

