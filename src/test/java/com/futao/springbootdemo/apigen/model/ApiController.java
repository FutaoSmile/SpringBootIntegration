package com.futao.springbootdemo.apigen.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author futao
 * Created on 2019-05-15.
 */
@ToString
@Getter
@Setter
public class ApiController {
    private String tagName;
    private String tagDesc;
    private List<ApiMethod> apiMethodList;
}
