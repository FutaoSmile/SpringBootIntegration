package com.futao.springbootdemo.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.futao.springbootdemo.annotation.EnumStatus;
import com.futao.springbootdemo.model.enums.UserRoleEnum;
import com.futao.springbootdemo.model.enums.UserSexEnum;
import com.futao.springbootdemo.model.enums.UserStatusEnum;
import com.futao.springbootdemo.model.system.ErrorMessage;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

//@TableName(value = "futao_user")

/**
 * 用户实体
 *
 * @author futao
 * Created on 2018/9/20-15:39.
 */
@Document(indexName = User.ES_INDEX_NAME, type = User.ES_TYPE)
@Validated
public class User extends BaseEntity implements Comparable<User>, Cloneable {

    public static final String ES_TYPE = "user";
    public static final String ES_INDEX_NAME = "futao";

    /**
     * 用户名
     */
    @Size(min = 3, max = 8, message = ErrorMessage.LogicErrorMessage.USERNAME_LEN_ILLEGAL)
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
    @Range(min = 0, max = 300)
    private String age;
    /**
     * 手机号
     */
    @Size(max = 11, message = ErrorMessage.LogicErrorMessage.MOBILE_LEN_ILLEGAL)
    private String mobile;

    /**
     * 用户邮箱
     */
    @Email(message = ErrorMessage.LogicErrorMessage.EMAIL_ILLEGAL)
    private String email;
    /**
     * 用户地址
     */
    @NotNull
    private String address;

    /**
     * {@link UserStatusEnum}
     * 用户状态
     */
    @EnumStatus(value = UserStatusEnum.class, message = ErrorMessage.LogicErrorMessage.USER_STATUS_ENUM_ILLEGAL)
    private int status;

    /**
     * {@link UserSexEnum}
     * 性别
     */
    @EnumStatus(value = UserSexEnum.class, message = ErrorMessage.LogicErrorMessage.USER_SEX_ENUM_ILLEGAL)
    private int sex;

    /**
     * 角色
     * {@link UserRoleEnum}
     */
    @EnumStatus(value = UserRoleEnum.class, message = ErrorMessage.LogicErrorMessage.USER_ROLE_ENUM_ILLEGAL)
    private int role;

    /**
     * 2.实例代码块与实例代码块之间根据代码书写顺序依次执行
     */ {
        System.out.println("{}");
    }


    /**
     * 1.静态代码块优先于实例代码块
     */
    static {
        System.out.println("static{}");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println(this.hashCode() + "我被回收啦");
    }

    //3.必须执行完代码块才执行构造方法
//    public User(@Size(min = 3, max = 8, message = ErrorMessage.LogicErrorMessage.USERNAME_LEN_ILLEGAL) String username, String password, String age, @Size(max = 11, message = ErrorMessage.LogicErrorMessage.MOBILE_LEN_ILLEGAL) String mobile, @Email String email, @NotNull String address, int status, int sex, int role) {
//        this.username = username;
//        this.password = password;
//        this.age = age;
//        this.mobile = mobile;
//        this.email = email;
//        this.address = address;
//        this.status = status;
//        this.sex = sex;
//        this.role = role;
//    }

    @Override
    public int compareTo(@org.jetbrains.annotations.NotNull User o) {
        if (Integer.valueOf(this.getAge()) > Integer.valueOf(o.getAge())) {
            return 1;
        } else if (Integer.valueOf(this.getAge()).intValue() == Integer.valueOf(o.getAge()).intValue()) {
            return 0;
        }
        return -1;
    }

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }

    public User(String id, Timestamp createTime, Timestamp lastModifyTime, @Size(min = 3, max = 8, message = ErrorMessage.LogicErrorMessage.USERNAME_LEN_ILLEGAL) String username, String password, @Range(min = 0, max = 300) String age, @Size(max = 11, message = ErrorMessage.LogicErrorMessage.MOBILE_LEN_ILLEGAL) String mobile, @Email(message = ErrorMessage.LogicErrorMessage.EMAIL_ILLEGAL) String email, @NotNull String address, int status, int sex, int role) {
        super(id, createTime, lastModifyTime);
        this.username = username;
        this.password = password;
        this.age = age;
        this.mobile = mobile;
        this.email = email;
        this.address = address;
        this.status = status;
        this.sex = sex;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", age='" + age + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", sex=" + sex +
                ", role=" + role +
                "} " + super.toString();
    }
}
