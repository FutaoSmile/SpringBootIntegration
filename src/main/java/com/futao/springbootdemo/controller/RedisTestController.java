package com.futao.springbootdemo.controller;

import com.futao.springbootdemo.service.RedisService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author futao
 * Created on 2019-03-14.
 */
@RequestMapping("redis")
@RestController
public class RedisTestController {

    @Resource
    private RedisService redisService;

    @PostMapping("listAdd")
    public Long listAdd(@RequestParam("key") String key, @RequestParam("value") String value) {
        return redisService.listAdd(key, value);
    }

    @DeleteMapping("listRemove")
    public String listRemove(@RequestParam("key") String key) {
        return redisService.listRemove(key);
    }

    @GetMapping("list")
    public Set<String> listAdd() {
        return redisService.list();
    }
}
