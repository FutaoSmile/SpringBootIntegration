package com.futao.springmvcdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.annotation.Sign;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author futao
 * Created on 2018/9/18-17:15.
 */
@RestController
@RequestMapping(path = "World", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class WorldController {

    @Sign
    @RequestMapping(value = "post", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject post(
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("appkey") String appkey,
            @RequestParam("sign") String sign
    ) {
        JSONObject object = new JSONObject();
        object.put("code", 0);
        object.put("result", "请求成功");
        return object;
    }
}
