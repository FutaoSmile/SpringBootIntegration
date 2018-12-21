package com.futao.springmvcdemo.model.entity;

import java.util.List;

/**
 * @author futao
 * Created on 2018-12-19.
 */
public class ApiControllerDescription extends BaseEntity {

    /**
     * 控制器描述=取@Api("xxx")
     */
    private String controllerDescription;

    /**
     * 打在controller上的路径
     */
    private List<String> baseUrl;

    /**
     * 控制器名称=取Class.simpleName
     */
    private String controllerName;

    /**
     * controller下包含的方法
     */
    private List<ApiMethodDescription> methodDescriptionList;

    public ApiControllerDescription(String controllerDescription, List<String> baseUrl, String controllerName, List<ApiMethodDescription> methodDescriptionList) {
        this.controllerDescription = controllerDescription;
        this.baseUrl = baseUrl;
        this.controllerName = controllerName;
        this.methodDescriptionList = methodDescriptionList;
    }

    public String getControllerDescription() {
        return controllerDescription;
    }

    public void setControllerDescription(String controllerDescription) {
        this.controllerDescription = controllerDescription;
    }

    public List<String> getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(List<String> baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public List<ApiMethodDescription> getMethodDescriptionList() {
        return methodDescriptionList;
    }

    public void setMethodDescriptionList(List<ApiMethodDescription> methodDescriptionList) {
        this.methodDescriptionList = methodDescriptionList;
    }
}
