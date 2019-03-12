package com.futao.springbootdemo.model.entity;


import java.util.List;

/**
 * 角色
 *
 * @author futao
 * Created on 2018-12-11.
 */
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色描述
     */
    private String roleDescription;

    /**
     * 角色拥有的权限
     */
    private List<Permission> permissions;

    /**
     * 拥有该角色的用户
     */
    private List<User> users;


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

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
