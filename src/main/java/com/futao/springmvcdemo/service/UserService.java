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

    /**
     * 用户注册
     *
     * @param username 用户名
     * @param age      年龄
     * @param mobile   手机号
     * @param email    邮箱
     * @param address  地址
     * @return 是否注册成功
     */
    boolean register(String username, String age, String mobile, String email, String address) throws InterruptedException;

    User login(String mobile, String password, HttpServletRequest request);

    /**
     * 获取用户列表
     *
     * @return
     */
    List<User> list(String mobile, int pageNum, int pageSize, String orderBy);

    int total();
}
