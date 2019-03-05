package com.futao.springbootdemo.model.entity;

import lombok.Data;

/**
 * 评论
 *
 * @author futao
 * Created on 2019-03-01.
 */
@Data
public class Review extends BaseEntity {

    /**
     * 评论的文章
     */
    private Article article;

    /**
     * 发表评论的用户
     */
    private User user;

    /**
     * 评论的内容
     */
    private String content;

    /**
     * 对于评论的评论(评论的回复)
     */
    private Review parentReview;
}
