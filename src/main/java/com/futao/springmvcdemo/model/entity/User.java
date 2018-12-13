package com.futao.springmvcdemo.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author futao
 * Created on 2018/9/20-15:39.
 * 用户实体
 */
@Validated
@AllArgsConstructor
public class User extends BaseEntity {

    /**
     * 用户名
     */
    @Size(min = 3, max = 8, message = ErrorMessage.USERNAME_LEN_ILLEGAL)
    private String username;

    /**
     * 密码
     *
     * @JsonIgnore
     * @Transient
     */
    @JSONField(serialize = false)
    private String password;
    /**
     * 年龄
     */
    private String age;
    /**
     * 手机号
     */
    @Size(max = 11, message = ErrorMessage.MOBILE_LEN_ILLEGAL)
    private String mobile;

    /**
     * 用户邮箱
     */
    @Email
    private String email;
    /**
     * 用户地址
     */
    @NotNull
    private String address;

    /**
     * {@link com.futao.springmvcdemo.model.enums.User_Status}
     * 用户状态
     */
    private int status;

    /**
     * {@link com.futao.springmvcdemo.model.enums.User_Sex}
     * 性别
     */
    private int sex;

    /**
     * 角色
     * {@link com.futao.springmvcdemo.model.enums.User_Role}
     */
    private int role;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
