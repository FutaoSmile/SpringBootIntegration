package com.futao.springbootdemo.model.entity;


import java.util.List;

/**
 * 权限
 *
 * @author futao
 * Created on 2018-12-18.
 */
public class Permission extends BaseEntity {

    /**
     * 权限名称,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
     */
    private String permissionName;
    /**
     * 权限描述
     */
    private String permissionDescription;

    /**
     * 资源类型，[menu|button]
     */
    private String resourceType;

    /**
     * 父编号
     */
    private Long parentId;

    /**
     * 父编号列表
     */
    private String parentIds;

    /**
     * 拥有该权限的角色
     */
    private List<Role> roles;


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

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
