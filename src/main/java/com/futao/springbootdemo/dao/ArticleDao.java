package com.futao.springbootdemo.dao;

import com.futao.springbootdemo.model.entity.Article;
import com.futao.springbootdemo.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 文章
 *
 * @author futao
 * Created on 2018/10/20.
 */
@Mapper
public interface ArticleDao {

    void add(Article article);

    /**
     * 查询列表
     *
     * @return
     */
    List<Article> list();

    /**
     * 通过id查询文章信息
     *
     * @param id
     * @return
     */
    Article getById(String id);

    /**
     * 查询用户发表的文章列表
     *
     * @param user 用户
     * @return
     */
    List<Article> byUser(User user);
}
