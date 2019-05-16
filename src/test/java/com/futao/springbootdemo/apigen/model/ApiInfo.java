package com.futao.springbootdemo.apigen.model;

import lombok.*;

import java.util.List;

/**
 * @author futao
 * Created on 2019-05-15.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiInfo {
    private String basePath;
    private String host;
    private String version;
    private String title;
    private String termsOfService;
    private String license;
    private String description;
    private String contact;
    private List<ApiController> apiControllerList;
}
