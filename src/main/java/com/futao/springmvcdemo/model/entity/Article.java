package com.futao.springmvcdemo.model.entity;

import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author futao
 * Created on 2018/10/20.
 * 文章
 * indexName=database
 * type=table
 * row=document
 * colnum=field
 */
@Document(indexName = Article.ES_INDEX_NAME, type = Article.ES_TYPE)
public class Article extends BaseEntity {

    public static final String ES_TYPE = "article";
    public static final String ES_INDEX_NAME = "futao";

    /**
     * 标题
     */
    private String title;
    /**
     * 简介
     */
    private String description;
    /**
     * 内容
     */
    private String content;

    /**
     * 作者用户id
     */
    private String authorId;

    /**
     * 浏览量
     */
    private int visitTimes;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }
}
