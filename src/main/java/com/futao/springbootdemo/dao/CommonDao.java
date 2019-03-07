package com.futao.springbootdemo.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;

/**
 * @author futao
 * Created on 2019-03-06.
 */
@Mapper
public interface CommonDao {
    /**
     * 查询某张表的所有数据
     *
     * @param tableName 表名
     * @return
     */
    ArrayList listAll(String tableName);
}
