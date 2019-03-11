package com.futao.springbootdemo.dao;

import com.futao.springbootdemo.model.entity.Tag;

import java.util.ArrayList;

/**
 * 标签
 *
 * @author futao
 * Created on 2019-03-06.
 */
public interface TagDao {

    /**
     * 通过文章id查询被标记的tagList
     *
     * @param articleId 文章id
     * @return 关联的标签列表
     */
    ArrayList<Tag> selectTagByArticleId(String articleId);
}
