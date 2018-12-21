package com.futao.springmvcdemo.model.entity;


import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author futao
 * Created on 2018-12-11.
 * 角色
 */
@TableName("futao_role")
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDescription;


    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }
}
