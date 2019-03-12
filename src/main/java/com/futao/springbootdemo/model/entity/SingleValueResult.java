package com.futao.springbootdemo.model.entity;

import java.io.Serializable;

/**
 * 返回简单数据类型
 * eg. String,int
 *
 * @author futao
 * Created on 2018/9/25-14:23.
 */
public class SingleValueResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    private T result;

    public SingleValueResult(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

}
