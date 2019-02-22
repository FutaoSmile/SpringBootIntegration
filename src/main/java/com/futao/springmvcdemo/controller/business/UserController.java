package com.futao.springmvcdemo.controller.business;

import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.annotation.IllegalValueCheck;
import com.futao.springmvcdemo.annotation.LoginUser;
import com.futao.springmvcdemo.annotation.Role;
import com.futao.springmvcdemo.model.entity.PageResultList;
import com.futao.springmvcdemo.model.entity.SingleValueResult;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.enums.UserRoleEnum;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
        return new SingleValueResult("success");
    }

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param age      年龄
     * @param mobile   手机号
     * @param email    邮箱
     * @param address  地址
     * @return
     */
    @ApiOperation("通过邮箱注册")
    @PostMapping("registerByEmail")
    public SingleValueResult registerByEmail(
            /*使用@RequestBody注解需要保证该对象有默认的空的构造函数
             * 是流的形式读取，那么流读了一次就没有了
             * */
            @RequestParam("username")
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
            @IllegalValueCheck(forbidden = "LOL")
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

        return new SingleValueResult("注册成功");
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
        try {
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(mobile, password));
        } catch (Exception e) {
            System.out.println(e);
        }
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
        jsonObject.put("当前的登陆的用户是:", userService.currentUser());
        return jsonObject;
    }


    /**
     * 添加评论
     *
     * @param content
     * @return
     */
    @ApiOperation("敏感词检测")
    @PostMapping(path = "addReview")
    public SingleValueResult addReview(
            @RequestParam("content")
            @IllegalValueCheck(forbidden = "亚索")
                    String content) {
        System.out.println("已经被执行");
        SingleValueResult result = new SingleValueResult(content);
        return result;
    }
}
