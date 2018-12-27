package com.futao.springmvcdemo.model.entity;

import java.io.Serializable;

/**
 * @author futao
 * Created on 2018-12-19.
 * 接口方法描述
 */
public class ApiMethodDescription implements Serializable {
    /**
     * 方法的名称
     */
    private String methodName;

    /**
     * 注解ApiOperation值
     */
    private String methodDescription;

    /**
     * value() or path()
     */
    private String[] methodUrl;

    public ApiMethodDescription(String methodName, String methodDescription, String[] methodUrl) {
        this.methodName = methodName;
        this.methodDescription = methodDescription;
        this.methodUrl = methodUrl;
    }


    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription;
    }

    public String[] getMethodUrl() {
        return methodUrl;
    }

    public void setMethodUrl(String[] methodUrl) {
        this.methodUrl = methodUrl;
    }
}
