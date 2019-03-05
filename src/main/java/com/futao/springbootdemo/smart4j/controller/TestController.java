package com.futao.springbootdemo.smart4j.controller;

import com.futao.springbootdemo.smart4j.annotation.SmartAction;
import com.futao.springbootdemo.smart4j.annotation.SmartController;

/**
 * @author futao
 * Created on 2019-01-05.
 */
@SmartController
public class TestController {

    @SmartAction("niubi")
    public void test() {

    }
}
