package com.futao.springmvcdemo.model.enums;

/**
 * @author futao
 * Created on 2018-12-11.
 * <p>
 * 用户性别
 */
public enum User_Sex {
    /**
     * 0=未知
     */
    Not_Set(0, "未知"),
    /**
     * 1=男
     */
    Men(1, "男"),
    /**
     * 2=女
     */
    Women(2, "女");

    private int code;
    private String description;

    User_Sex(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
