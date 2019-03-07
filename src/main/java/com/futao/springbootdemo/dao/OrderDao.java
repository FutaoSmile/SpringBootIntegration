/*
 * Copyright (c) 2018.
 */

package com.futao.springbootdemo.dao;

import com.futao.springbootdemo.model.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

/**
 * 订单操作
 *
 * @author futao
 * Created on 2018/11/20.
 */
@Mapper
public interface OrderDao {

    /**
     * 根据erpOrderid查询订单信息
     *
     * @param erpOrderId 进销存订单编号
     * @return 订单实体
     */
    @Select("select * from futao_order where erpOrderId=#{erpOrderId}")
    Order queryIfExistByErpOrderId(@Param("erpOrderId") String erpOrderId);

    /**
     * 新增订单
     *
     * @param id             订单id
     * @param userId         用户id
     * @param erpOrderId     进销存订单id
     * @param createTime     创建时间
     * @param lastModifyTime 最后修改时间
     * @return 插入的条数
     */
    @Insert("insert " +
            "into futao_order(id,userId," +
            "erpOrderId,createTime,lastModifyTime) " +
            "values(#{id},#{userId},#{erpOrderId}," +
            "#{createTime},#{lastModifyTime})")
    int add(@Param("id") String id, @Param("userId") String userId,
            @Param("erpOrderId") String erpOrderId,
            @Param("createTime") Timestamp createTime, @Param("lastModifyTime") Timestamp lastModifyTime);

    /**
     * 新增订单
     *
     * @param id
     * @param userId
     * @param erpOrderId
     * @param createTime
     * @param lastModifyTime
     * @return
     */
    @Insert("insert " +
            "into futao_order(id,userId,erpOrderId," +
            "createTime,lastModifyTime)  " +
            "select #{id},#{userId},#{erpOrderId}," +
            "#{createTime},#{lastModifyTime} " +
            " where not exists (" +
            "select id from futao_order where erpOrderId=#{erpOrderId})")
    int addOrder(@Param("id") String id, @Param("userId") String userId,
                 @Param("erpOrderId") String erpOrderId,
                 @Param("createTime") Timestamp createTime, @Param("lastModifyTime") Timestamp lastModifyTime);

    /**
     * ByDuplicateKey
     *
     * @param id
     * @param userId
     * @param erpOrderId
     * @param remark
     * @param createTime
     * @param lastModifyTime
     * @return
     */
    @Insert("insert into futao_order (id, userId, erpOrderId,remark, createTime, lastModifyTime)" +
            "values (#{id}, #{userId}, #{erpOrderId}, #{remark},#{createTime}, #{lastModifyTime})" +
            "on duplicate key update erpOrderId = #{erpOrderId},remark=#{remark}")
    int addOrUpdateByDuplicateKey(@Param("id") String id, @Param("userId") String userId,
                                  @Param("erpOrderId") String erpOrderId, @Param("remark") String remark,
                                  @Param("createTime") Timestamp createTime, @Param("lastModifyTime") Timestamp lastModifyTime);


    /**
     * ByReplace
     *
     * @param id
     * @param userId
     * @param erpOrderId
     * @param remark
     * @param createTime
     * @param lastModifyTime
     * @return
     */
    @Insert("replace into futao_order (id, userId, erpOrderId,remark, createTime, lastModifyTime)" +
            "values (#{id}, #{userId}, #{erpOrderId}, #{remark},#{createTime}, #{lastModifyTime})")
    int addOrUpdateByReplace(@Param("id") String id, @Param("userId") String userId,
                             @Param("erpOrderId") String erpOrderId, @Param("remark") String remark,
                             @Param("createTime") Timestamp createTime, @Param("lastModifyTime") Timestamp lastModifyTime);


}