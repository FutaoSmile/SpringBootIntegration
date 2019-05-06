package com.futao.springbootdemo.apigen.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

/**
 * 每一个接口方法
 *
 * @author futao
 * Created on 2019-04-28.
 */
@Getter
@Setter
public class ApiMethod {
    /**
     * 描述
     */
    private String desc;
    /**
     * 地址
     */
    private String url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 参数列表
     */
    private JSONObject parameters;

}
