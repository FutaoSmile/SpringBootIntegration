package com.futao.springmvcdemo.model.entity;

import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * @author futao
 * Created on 2018/9/25-14:23.
 */
public class SingleValueResult implements RestfullResult{

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
