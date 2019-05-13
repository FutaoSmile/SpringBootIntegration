package com.futao.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.futao.springbootdemo.model.entity.SingleValueResult;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.service.ExportExcelService;
import com.futao.springbootdemo.service.TestService;
import com.futao.springbootdemo.service.impl.RabbitMqServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
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

    @Resource
    private TestService testService;

    @GetMapping("select")
    public void select() {
        testService.select();
    }

    @Resource
    private RabbitMqServiceImpl rabbitMqService;


    /**
     * 行级锁select for update测试-未使用行级锁
     *
     * @return
     */
    @PostMapping("notSelect4Update")
    public boolean notSelect4Update() {
        return testService.notSelect4Update();
    }

    /**
     * 行级锁select for update测试-中途修改数据
     */
    @PostMapping("afterSelect")
    public void afterSelect() {
        testService.afterSelect();
    }

    /**
     * 行级锁select for update测试-使用行级锁
     */
    @PostMapping("select4Update")
    public void select4Update() {
        testService.select4Update();
    }

    @PostMapping("sync2Mq")
    public void sync2Mq() {
        rabbitMqService.syncMsg2Mq();
    }

    /**
     * 发送消息到RabbitMq消息队列
     *
     * @param msg 消息
     * @return
     */
    @ApiOperation("发送消息到RabbitMq消息队列")
    @PostMapping("rabbitSender")
    public SingleValueResult<String> rabbitMqSender(
            @NotNull
            @RequestParam
                    String routingKey,
            @NotNull
            @RequestParam
                    String msg
    ) {
        testService.sendMsgByRabbit(routingKey, msg);
        return new SingleValueResult<>(msg);
    }

    @GetMapping("transaction")
    public int transaction(@RequestParam("amount") int amount) {
        return testService.transactionTest(amount);
    }

    @GetMapping("list")
    public void list() {
        testService.list();
    }

    @GetMapping("batchInsert")
    public void batchInsert() {
        testService.batchInsert();
    }


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
        Object[] object = new Object[]{user.getUsername(), user.getAge(), user.getRole(), user.getAddress(), user.getMobile(), user.getEmail()};
        List<List<Object>> list = new ArrayList<>();
//        list.add(object);
//        list.add(object);
//        list.add(object);
//        list.add(object);
//        list.add(object);
//        list.add(object);
//        list.add(object);

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
