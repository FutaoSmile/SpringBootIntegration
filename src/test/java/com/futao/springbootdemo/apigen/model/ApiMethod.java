package com.futao.springbootdemo.apigen.model;

import lombok.*;

import java.util.List;
import java.util.Set;

/**
 * @author futao
 * Created on 2019-05-15.
 */
@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiMethod {
    private Set<String> supportMethods;
    private String summary;
    private ApiParameter[] parameters;
    private String response;
    private String path;
    private List<String> consumers;
    private List<String> produces;
}
