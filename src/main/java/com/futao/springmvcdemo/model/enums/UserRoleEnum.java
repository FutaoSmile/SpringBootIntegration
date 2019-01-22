package com.futao.springmvcdemo.model.enums;

import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;

/**
 * 角色
 *
 * @author futao
 * Created on 2018/9/19-14:41.
 */
public enum UserRoleEnum {
    /**
     * 普通登录用户
     */
    NORMAL(1, "普通用户"),
    /**
     * 管理员用户
     */
    ADMIN(2, "管理员");

    private int type;
    private String description;

    UserRoleEnum(int type, String description) {
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
    public static UserRoleEnum value(int roleType) {
        if (roleType == UserRoleEnum.NORMAL.getType()) {
            return UserRoleEnum.NORMAL;
        } else if (roleType == UserRoleEnum.ADMIN.getType()) {
            return UserRoleEnum.ADMIN;
        }
        throw LogicException.le(ErrorMessage.ROLE_NOT_EXIST);
    }
}
