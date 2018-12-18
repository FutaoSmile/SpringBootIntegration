package com.futao.springmvcdemo.service;

import com.futao.springmvcdemo.model.entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author futao
 * Created on 2018/9/20-15:17.
 */
public interface UserService {
    User currentUser();


    User getUserByMobile(String mobile);

    User getUserById(String id);

    void registerByEmail(String username, String password, int age, String mobile, String email, String address, String verifyCode, int sex);

    User login(String mobile, String password, HttpServletRequest request);

    /**
     * 获取用户列表
     *
     * @return
     */
    List list(String mobile, int pageNum, int pageSize, String orderBy);

    int total();

    User userNameLogin(User user, HttpServletRequest request);

    void sendRegisterEmailVerifyCode(String email);
}
