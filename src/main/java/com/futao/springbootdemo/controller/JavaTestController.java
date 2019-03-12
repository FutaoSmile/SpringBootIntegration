package com.futao.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.model.entity.SingleValueResult;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.service.ExportExcelService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author futao
 * Created on 2019-01-16.
 */
@RestController
@Api("java测试接口")
@RequestMapping(path = "test")
public class JavaTestController {


    @GetMapping("parameterTest")
    public SingleValueResult<String> parameterTest(@RequestParam("annParam") String annParam, String simpleAnn) {
        return new SingleValueResult<>(annParam + simpleAnn);
    }

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


    @Resource
    private ExportExcelService exportExcelService;

    @GetMapping(path = "exportExcel")
    public void exportExcel(
            @RequestParam("fileName") String fileName,
            HttpServletResponse response) {
        User user = new User();
        Object[] object = new Object[]{user.getUsername(), user.getAge(), user.getRoles(), user.getAddress(), user.getMobile(), user.getEmail()};
        ArrayList<Object[]> list = new ArrayList<>();
        list.add(object);
        list.add(object);
        list.add(object);
        list.add(object);
        list.add(object);
        list.add(object);
        list.add(object);

        String[] head = new String[]{"姓名", "年龄", "角色", "地址", "电话", "邮箱"};
        exportExcelService.export(fileName, "我是sheet", head, list, response);
    }

    @GetMapping(path = "exportExcelObj")
    public void exportExcelObj(
            @RequestParam("fileName") String fileName,
            HttpServletResponse response) throws NoSuchMethodException {
        String[] head = new String[]{"姓名", "年龄", "角色", "地址", "电话", "邮箱"};
        Method[] methods = new Method[]{User.class.getMethod("getUsername"), User.class.getMethod("getAge")};
        User user = new User();
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        users.add(user);
        user.setUsername("我是");
        user.setAge("121");
        exportExcelService.export(fileName, "我是sheet", head, methods, users, response);
    }
}
