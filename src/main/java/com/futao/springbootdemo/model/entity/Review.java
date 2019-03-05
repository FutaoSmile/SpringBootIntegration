package com.futao.springbootdemo.model.entity;

import java.sql.Timestamp;

/**
 * 评论
 *
 * @author futao
 * Created on 2019-03-01.
 */
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

    public Review(String id, Timestamp createTime, Timestamp lastModifyTime, Article article, User user, String content, Review parentReview) {
        super(id, createTime, lastModifyTime);
        this.article = article;
        this.user = user;
        this.content = content;
        this.parentReview = parentReview;
    }

    public Review(Article article, User user, String content, Review parentReview) {
        this.article = article;
        this.user = user;
        this.content = content;
        this.parentReview = parentReview;
    }

    public Review() {
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Review getParentReview() {
        return parentReview;
    }

    public void setParentReview(Review parentReview) {
        this.parentReview = parentReview;
    }
}
