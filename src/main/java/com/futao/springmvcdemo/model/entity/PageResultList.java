package com.futao.springmvcdemo.model.entity;

import java.util.List;

/**
 * @author futao
 * Created on 2018/10/9.
 * 分页数据结构
 */
public class PageResultList<T> {
    /**
     * 数据
     */
    private List<T> list;
    /**
     * 数据总量
     */
    private int total;

    public PageResultList(List<T> list, int total) {
        this.list = list;
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
