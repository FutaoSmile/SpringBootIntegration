package com.futao.springmvcdemo.controller;

import com.futao.springmvcdemo.model.entity.SingleValueResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2018/10/24.
 */
@RestController
@RequestMapping(path = "/")
public class PathTestController {

    @RequestMapping("path/path")
    public SingleValueResult path() {
        return new SingleValueResult("success");
    }
}
