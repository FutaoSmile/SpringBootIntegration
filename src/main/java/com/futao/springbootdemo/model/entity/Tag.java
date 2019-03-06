package com.futao.springbootdemo.model.entity;

import java.sql.Timestamp;

/**
 * 标签
 *
 * @author futao
 * Created on 2019-03-06.
 */
public class Tag extends BaseEntity {
    /**
     * 标签名称
     */
    private String tagName;

    public Tag(String id, Timestamp createTime, Timestamp lastModifyTime, String tagName) {
        super(id, createTime, lastModifyTime);
        this.tagName = tagName;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Tag() {
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }
}
