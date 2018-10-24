package com.futao.springmvcdemo.model.entity;

import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author futao
 * Created on 2018/10/20.
 * 文章
 * indexName=database
 * type=table
 * row=document
 * colnum=field
 */
@Document(indexName = "futao", type = "article")
public class Article extends BaseEntity {
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
}
