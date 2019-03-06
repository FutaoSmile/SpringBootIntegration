package com.futao.springbootdemo.model.entity;

/**
 * 返回简单数据类型
 * eg. String,int
 *
 * @author futao
 * Created on 2018/9/25-14:23.
 */
public class SingleValueResult {

    private Object result;

    public SingleValueResult(Object result) {
        this.result = result;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}
