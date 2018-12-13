package com.futao.springmvcdemo.model.enums;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;

/**
 * @author futao
 * Created on 2018/9/19-14:41.
 * 角色
 */
public enum User_Role {
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

    User_Role(int type, String description) {
        this.type = type;
        this.description = description;
    }

    public int getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 通过type来查询枚举值
     *
     * @param roleType
     * @return
     */
    public static User_Role value(int roleType) {
        if (roleType == User_Role.Normal.getType()) {
            return User_Role.Normal;
        } else if (roleType == User_Role.Admin.getType()) {
            return User_Role.Admin;
        }
        throw LogicException.le(ErrorMessage.ROLE_NOT_EXIST);
    }

}
