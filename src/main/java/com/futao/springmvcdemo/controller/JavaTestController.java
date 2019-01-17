package com.futao.springmvcdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.futao.springmvcdemo.model.entity.User;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

/**
 * @author futao
 * Created on 2019-01-16.
 */
@RestController
@Api("java测试接口")
@RequestMapping(path = "test")
public class JavaTestController {

    @GetMapping(path = "get")
    public JSONObject get(@RequestParam("p1") String p1, @RequestParam("p2") int p2) {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.fluentPut("p1", p1).fluentPut("p2", p2);
    }

    @PostMapping(path = "postParameter")
    public JSONObject postParameter(@RequestParam("p1") String p1, @RequestParam("p2") int p2) {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.fluentPut("p1", p1).fluentPut("p2", p2);
    }

    @PostMapping(path = "postObject")
    public User postObject(
            @RequestBody User user, @RequestParam("p1") String p1
    ) {
        user.setAddress(p1);
        return user;
    }

    @DeleteMapping(path = "deleteParameter")
    public JSONObject deleteParameter(
            @RequestParam("p1") String p1, @RequestParam("p2") int p2
    ) {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.fluentPut("p1", p1).fluentPut("p2", p2);
    }

    @DeleteMapping("deletePathVariable/{id}")
    public JSONObject deletePathVariable(
            @RequestParam("p1") String p1, @PathVariable("id") int id
    ) {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.fluentPut("p1", p1).fluentPut("id", id);
    }


    @PutMapping("put")
    public JSONObject put(
            @RequestParam("p1") String p1, @RequestParam("p2") int p2
    ) {
        JSONObject jsonObject = new JSONObject();
        return jsonObject.fluentPut("p1", p1).fluentPut("id", p2);
    }
}
