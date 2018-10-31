package com.futao.springmvcdemo.model.entity;

/**
 * @author futao
 * Created on ${date}.
 */
//@Entity
//@Table(name = "futao_revire")
//@DynamicInsert
//@DynamicUpdate
public class Review extends BaseEntity {

    //    @JoinColumn(name = "article_id")
    private Article article;

    //    @JoinColumn(name = "user_id")
    private User user;

    //    @Column(name = "content")
    private String content;

    //    @JoinColumn(name = "p_article_id")
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
