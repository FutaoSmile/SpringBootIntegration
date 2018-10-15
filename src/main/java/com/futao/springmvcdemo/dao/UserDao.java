package com.futao.springmvcdemo.dao;

import com.futao.springmvcdemo.model.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author futao
 * Created on 2018/9/20-16:00.
 */
@Mapper
public interface UserDao {

    /**
     * 查询用户列表
     *
     * @param mobile
     * @param start
     * @param limit
     * @return
     */
    @Select("select " +
            "* " +
            "from futao_user " +
            "where mobile like '%${mobile}%' " +
            "order by createtime desc " +
            "limit #{start},#{limit}")
    List<User> list(@Param("mobile") String mobile, @Param("start") int start, @Param("limit") int limit);


    /**
     * 按表名查询数据量
     *
     * @return
     */
    @Select("select count(*) from ${tableName}")
    int total(@Param("tableName") String tableName);

    /**
     * 用户注册
     *
     * @param id       用户id
     * @param username 用户名
     * @param age      年龄
     * @return
     */
    @Insert("insert " +
            "into futao_user(id,username,age,mobile,email,address,createtime,lastmodifytime) " +
            "values(#{id},#{username},#{age},#{mobile},#{email},#{address},#{createtime},#{lastmodifytime})")
    int addUser(@Param("id") String id, @Param("username") String username, @Param("age") String age,
                @Param("mobile") String mobile, @Param("email") String email, @Param("address") String address,
                @Param("createtime") Timestamp createTime, @Param("lastmodifytime") Timestamp lastmodifytime);

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

    /**
     * 通过id查询用户信息
     *
     * @param id
     * @return
     */
    @Select("select " +
            "id,createTime,lastModifyTime,username,age,mobile,email,address " +
            "from " +
            "futao_user " +
            "where " +
            "id=#{id}")
    User getUserById(@Param("id") String id);


    /**
     * 通过手机号和密码查询用户信息
     *
     * @param mobile   用户手机号
     * @param password 密码
     * @return
     */
    @Select("select " +
            "* " +
            "from " +
            "futao_user " +
            "where " +
            "mobile=#{mobile} " +
            "and password=#{password}")
    User getUserByMobileAndPwd(@Param("mobile") String mobile, @Param("password") String password);
}
