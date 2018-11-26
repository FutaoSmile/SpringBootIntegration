package com.futao.springmvcdemo.model.system;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author futao
 * Created on 2018/10/16.
 */
public class ErrorMessageFields implements Serializable {
    private static final long serialVersionUID = 5691087128795914641L;
    private String fieldName;
    private String value;

    public ErrorMessageFields(String fieldName, String value) {
        this.fieldName = fieldName;
        this.value = value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
