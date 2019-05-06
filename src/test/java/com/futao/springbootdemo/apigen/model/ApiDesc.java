package com.futao.springbootdemo.apigen.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author futao
 * Created on 2019-04-28.
 */
@Getter
@Setter
public class ApiDesc {

    private String baseUrl;

    private String title;

    private Timestamp createTime;

    private String desc;

    private String version;

    private List<ApiController> apiControllerList;
}
