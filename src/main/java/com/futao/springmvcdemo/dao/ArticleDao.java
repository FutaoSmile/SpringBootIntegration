package com.futao.springmvcdemo.dao;

import com.futao.springmvcdemo.model.entity.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author futao
 * Created on 2018/10/20.
 * 文章
 */
@Mapper
public interface ArticleDao{
    /**
     * 新增文章
     *
     * @param id
     * @param title
     * @param desc
     * @param content
     * @param createTime
     * @param lastModifyTime
     * @return
     */
    @Insert("insert " +
            "into futao_article(id,title,description,content,createtime,lastmodifytime) " +
            "values(#{id},#{title},#{description},#{content},#{crateTime},#{lastModifyTime})")
    int add(@Param("id") String id,
            @Param("title") String title,
            @Param("description") String desc,
            @Param("content") String content,
            @Param("crateTime") Timestamp createTime,
            @Param("lastModifyTime") Timestamp lastModifyTime
    );

    @Select("select * " +
            "from futao_article")
    List<Article> list();
}
