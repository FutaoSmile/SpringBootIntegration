package com.futao.springmvcdemo.model.entity;

import java.util.Collection;

/**
 * @author futao
 * Created on 2018/10/9.
 * 分页数据结构
 */
public class PageResultList<T> {
    /**
     * 数据
     */
    private Collection<T> list;
    /**
     * 数据总量
     */
    private int total;
    /**
     * 当前页码
     */
    private int currentPageNum;

    public PageResultList(Collection<T> list, int total, int currentPageNum) {
        this.list = list;
        this.total = total;
        this.currentPageNum = currentPageNum;
    }

    public Collection<T> getList() {
        return list;
    }

    public void setList(Collection<T> list) {
        this.list = list;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
