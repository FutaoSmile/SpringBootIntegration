package com.futao.springmvcdemo;

import com.fasterxml.jackson.annotation.JsonSubTypes;

import java.util.ArrayList;

/**
 * @author futao
 * Created on 2018-12-16.
 */
public class A extends ArrayList<String> {

    private String string;
    private ArrayList<String> list;


    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

//    @Override
//    public String toString() {
//        return "A{" +
//                "string='" + string + '\'' +
//                ", list=" + list +
//                '}';
//    }

}
