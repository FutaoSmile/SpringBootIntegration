package com.futao.springbootdemo.model.entity;

import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 文章
 * indexName=database
 * type=table
 * row=document
 * colnum=field
 * <p>
 *
 * @author futao
 * Created on 2018/10/20.
 */
@EqualsAndHashCode(callSuper = true)
@Validated
@Document(indexName = Article.ES_INDEX_NAME, type = Article.ES_TYPE)
public class Article extends BaseEntity {

    public static final String ES_TYPE = "article";
    public static final String ES_INDEX_NAME = "futao";

    /**
     * 标题
     */
    @Length(min = 1, max = 20)
    private String title;
    /**
     * 简介
     */
    @Length(max = 200)
    private String description;
    /**
     * 内容
     */
    @Length(min = 1, max = 5000)
    private String content;

    /**
     * 作者用户
     */
    @NotNull
    private User author;

    /**
     * 浏览量
     */
    private int visitTimes;

    /**
     * 标签
     */
    private List<Tag> tagList;


    public Article() {
    }

    public Article(@Length(min = 1, max = 20) String title, @Length(max = 200) String description, @Length(min = 1, max = 5000) String content, @NotNull User author, int visitTimes, List<Tag> tagList) {
        this.title = title;
        this.description = description;
        this.content = content;
        this.author = author;
        this.visitTimes = visitTimes;
        this.tagList = tagList;
    }

    public static String getEsType() {
        return ES_TYPE;
    }

    public static String getEsIndexName() {
        return ES_INDEX_NAME;
    }

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public int getVisitTimes() {
        return visitTimes;
    }

    public void setVisitTimes(int visitTimes) {
        this.visitTimes = visitTimes;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }
}
