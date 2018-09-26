package com.futao.springmvcdemo.dao;

import com.futao.springmvcdemo.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @author futao
 * Created on 2018/9/20-16:00.
 */
@Mapper
public interface UserDao {

    /**
     * 根据用户名查询用户列表
     *
     * @param username 用户名
     * @return
     */
    @Select("select " +
            "* " +
            "from futao_user")
    List<User> list();

    /**
     * 用户注册
     *
     * @param id       用户id
     * @param username 用户名
     * @param age      年龄
     * @return
     */
    @Insert("insert into futao_user(id,username,age,mobile,email,address) values(#{id},#{username},#{age},#{mobile},#{email},#{address})")
    int addUser(@Param("id") String id, @Param("username") String username, @Param("age") String age, @Param("mobile") String mobile, @Param("email") String email, @Param("address") String address);

    /**
     * 通过手机号查询用户信息
     *
     * @param mobile 用户手机号
     * @return
     */
    @Select("select " +
            "* " +
            "from " +
            "futao_user " +
            "where " +
            "mobile=#{mobile}")
    User getUserByMobile(@Param("mobile") String mobile);
}
