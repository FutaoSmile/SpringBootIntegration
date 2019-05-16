package com.futao.springbootdemo.apigen.model;

import lombok.*;

/**
 * @author futao
 * Created on 2019-05-15.
 */
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApiParameter {
    private String name;
    private String type;
    private boolean required;
    private String desc;
}
