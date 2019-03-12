package com.futao.springbootdemo.controller.business;

import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.annotation.IllegalValueCheck;
import com.futao.springbootdemo.annotation.LoginUser;
import com.futao.springbootdemo.annotation.Role;
import com.futao.springbootdemo.foundation.ApplicationException;
import com.futao.springbootdemo.model.entity.PageResultList;
import com.futao.springbootdemo.model.entity.SingleValueResult;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.model.enums.UserRoleEnum;
import com.futao.springbootdemo.model.system.ErrorMessage;
import com.futao.springbootdemo.service.UserService;
import com.futao.springbootdemo.service.impl.UserServiceImpl;
import com.futao.springbootdemo.utils.CommonUtilsKt;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 用户
 *
 * @author futao
 * Created on 2018/9/19-15:05.
 */
@RequestMapping(path = "user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RestController
@Validated
@Api("用户")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 发送注册邮件验证码
     *
     * @param email 注册邮箱
     * @return
     */
    @ApiOperation("发送注册邮件验证码")
    @PostMapping("sendRegisterEmailVerifyCode")
    public SingleValueResult sendRegisterEmailVerifyCode(
            @RequestParam("email")
            @Email(message = ErrorMessage.LogicErrorMessage.EMAIL_ILLEGAL)
                    String email
    ) {
        userService.sendRegisterEmailVerifyCode(email);
        return new SingleValueResult<>("success");
    }

    /**
     * 用户注册
     *
     * @param username   用户名
     * @param age        年龄
     * @param mobile     手机号
     * @param email      邮箱
     * @param address    地址
     * @param verifyCode 验证码
     * @return
     */
    @ApiOperation("通过邮箱注册")
    @PostMapping("registerByEmail")
    public SingleValueResult<String> registerByEmail(
            /*使用@RequestBody注解需要保证该对象有默认的空的构造函数
             * 是流的形式读取，那么流读了一次就没有了
             * */
            @RequestParam("username")
            @IllegalValueCheck(forbidden = "admin")
            @Size(min = 2, max = 8, message = "{a.c}")
                    String username,
            @RequestParam("age")
            @Max(value = 300, message = ErrorMessage.LogicErrorMessage.AGE_ERROR)
                    int age,
            @Size(max = 11, message = ErrorMessage.LogicErrorMessage.MOBILE_LEN_ILLEGAL)
            @RequestParam("mobile")
                    String mobile,
            @RequestParam("email")
            @Email(message = ErrorMessage.LogicErrorMessage.EMAIL_ILLEGAL)
                    String email,
            @Size(max = 100, message = ErrorMessage.LogicErrorMessage.ADDRESS_LEN_TOO_LARGE)
            @RequestParam("address")
                    String address,
            @RequestParam("password")
            @Size(min = 8, message = ErrorMessage.LogicErrorMessage.PASSWORD_LEN)
                    String password,
            @RequestParam("sex")
                    int sex,
            @RequestParam("verifyCode")
            @NotNull
                    String verifyCode
    ) {
        userService.registerByEmail(username, password, age, mobile, email, address, verifyCode, sex);

        return new SingleValueResult<>("注册成功");
    }


    /**
     * 获取用户列表
     *
     * @return
     */
    @ApiOperation("用户列表")
    @GetMapping("list")
    @Role({UserRoleEnum.ADMIN, UserRoleEnum.NORMAL})
    public PageResultList<User> list(
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "orderBy", required = false) String orderBy,
            @RequestParam("pageNum") int pageNum,
            @RequestParam("pageSize") int pageSize
    ) {
        return new PageResultList<>(userService.list(mobile, pageNum, pageSize, orderBy), 1, 11);
    }

    /**
     * 登陆接口
     *
     * @param mobile
     * @param request
     * @return
     */
    @ApiOperation("手机登录")
    @PostMapping(path = "mobileLogin")
    public User mobileLogin(
            @RequestParam("mobile") String mobile,
            @RequestParam("password") String password,
            HttpServletRequest request
    ) {
        return userService.login(mobile, password, request);
    }

    @ApiOperation("RequestBody形式登录")
    @PostMapping(path = "login")
    public User userNameLogin(
            @RequestBody User user,
            HttpServletRequest request

    ) {
        return userService.userNameLogin(user, request);
    }

    /**
     * 获取当前的登陆的用户信息，其实是从threadLocal中获取
     *
     * @return
     */
    @ApiOperation("我的信息")
    @LoginUser
    @GetMapping(path = "my")
    public JSONObject my() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("当前的登陆的用户是:", userService.currentLoginUser());
        return jsonObject;
    }


    /**
     * shiroLogin 未实现
     *
     * @param mobile   手机号
     * @param password 密码
     * @return
     */
    @PostMapping("loginShiro")
    public User loginShiro(
            @RequestParam("mobile") String mobile,
            @RequestParam("password") String password
    ) {
        password = CommonUtilsKt.md5(password + UserServiceImpl.PWD_SALT);
        UsernamePasswordToken token = new UsernamePasswordToken(mobile, password, false);
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject != null) {
                subject.logout();
            }
            SecurityUtils.getSubject().login(token);
            return (User) SecurityUtils.getSubject().getPrincipal();
        } catch (AuthenticationException e) {
            throw ApplicationException.ae(e.getMessage());
        }
    }
}
