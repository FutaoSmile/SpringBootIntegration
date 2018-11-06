package com.futao.springmvcdemo.model.entity;

/**
 * @author futao
 * Created on ${date}.
 */
public class Review extends BaseEntity {

    private Article article;

    private User user;

    private String content;

    private Review pArticle;

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

    public Review getpArticle() {
        return pArticle;
    }

    public void setpArticle(Review pArticle) {
        this.pArticle = pArticle;
    }
}
