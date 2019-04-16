package com.futao.springbootdemo.model.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色
 *
 * @author futao
 * Created on 2018-12-11.
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Validated
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    @Size(min = 1, max = 20)
    private String roleName;

    /**
     * 角色描述
     */
    @Size(min = 1, max = 50)
    private String roleDescription;

    /**
     * 角色拥有的权限
     */
    private List<Permission> permissions;

}
