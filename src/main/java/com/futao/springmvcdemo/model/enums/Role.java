package com.futao.springmvcdemo.model.enums;

/**
 * @author futao
 * Created on 2018/9/19-14:41.
 * 角色
 */
public enum Role {
    /**
     * 普通登录用户
     */
    Normal(1, "普通用户"),
    /**
     * 管理员用户
     */
    Admin(2, "管理员");

    private int type;
    private String description;

    Role(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }
}
