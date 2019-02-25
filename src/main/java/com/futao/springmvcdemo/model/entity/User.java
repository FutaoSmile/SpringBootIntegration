package com.futao.springmvcdemo.model.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.futao.springmvcdemo.annotation.EnumStatus;
import com.futao.springmvcdemo.model.enums.UserRoleEnum;
import com.futao.springmvcdemo.model.enums.UserSexEnum;
import com.futao.springmvcdemo.model.enums.UserStatusEnum;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author futao
 * Created on 2018/9/20-15:39.
 * 用户实体
 */
@Validated
//@TableName(value = "futao_user")
@ToString
@Getter
@Setter
@AllArgsConstructor
public class User extends BaseEntity implements Comparable<User>, Cloneable {

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
        System.out.println("9999999999999");
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

    public User() {
    }

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
}
