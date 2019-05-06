package com.futao.springbootdemo.apigen.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 接口Controller
 *
 * @author futao
 * Created on 2019-04-28.
 */
@Getter
@Setter
public class ApiController {
    /**
     * 描述
     */
    private String desc;
    /**
     * 包含的接口列表
     */
    private List<ApiMethod> apiMethodList;
}