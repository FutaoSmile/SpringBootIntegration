package com.futao.springbootdemo.service;

import com.futao.springbootdemo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户
 *
 * @author futao
 * Created on 2018/9/20-15:17.
 */
public interface UserService {
    /**
     * 当前登录用户
     *
     * @return
     */
    User currentLoginUser();


    /**
     * 通过手机号查询用户
     *
     * @param mobile
     * @return
     */
    User getUserByMobile(String mobile);

    /**
     * 通过id查询用户
     *
     * @param id
     * @return
     */
    User getUserById(String id);

    /**
     * 通过邮箱进行注册
     *
     * @param username
     * @param password
     * @param age
     * @param mobile
     * @param email
     * @param address
     * @param verifyCode
     * @param sex
     */
    void registerByEmail(String username, String password, int age, String mobile, String email, String address, String verifyCode, int sex);

    /**
     * 获取用户列表
     *
     * @param mobile
     * @param pageNum
     * @param pageSize
     * @param orderBy
     * @return
     */
    List list(String mobile, int pageNum, int pageSize, String orderBy);

    /**
     * total
     *
     * @return
     */
    int total();

    User login(String mobile, String password, HttpServletRequest request, HttpServletResponse response);

    /**
     * 用户登录
     *
     * @param user
     * @param request
     * @return
     */
    User userNameLogin(User user, HttpServletRequest request);

    /**
     * 发送注册邮件验证码
     *
     * @param email
     */
    void sendRegisterEmailVerifyCode(String email);

}
