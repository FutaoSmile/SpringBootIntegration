package com.futao.springmvcdemo.controller.business;

import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.annotation.Sign;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author futao
 * Created on 2018/9/18-14:45.
 */
@RestController
@RequestMapping("Hello")
@Sign
@ApiIgnore
public class HelloController {

    @RequestMapping(value = "get", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public JSONObject get(
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

    @GetMapping("cc")
    public JSONObject cc() {
        throw LogicException.le(ErrorMessage.NOT_LOGIN);
    }
}
