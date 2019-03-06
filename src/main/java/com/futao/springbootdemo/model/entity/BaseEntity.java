package com.futao.springbootdemo.model.entity;

import com.futao.springbootdemo.service.UUIDService;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 实体基类
 *
 * @author futao
 * Created on 2018/9/22-15:17.
 */
@ToString
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 7360778825666258407L;
    /**
     * 唯一主键id
     */
    private String id = UUIDService.get();
    /**
     * 创建时间
     */
    private Timestamp createTime;
    /**
     * 最后修改时间
     */
    private Timestamp lastModifyTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Timestamp lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public BaseEntity(String id, Timestamp createTime, Timestamp lastModifyTime) {
        this.id = id;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
    }

    public BaseEntity() {
    }
}
