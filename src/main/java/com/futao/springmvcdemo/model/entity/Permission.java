package com.futao.springmvcdemo.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author futao
 * Created on 2018-12-18.
 * 权限
 */
@TableName("futao_permission")
public class Permission extends BaseEntity {

    /**
     * 权限名称
     */
    private String permissionName;
    /**
     * 权限描述
     */
    private String permissionDescription;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }

    public void setPermissionDescription(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }
}
