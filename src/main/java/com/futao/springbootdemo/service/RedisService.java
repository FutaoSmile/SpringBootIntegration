package com.futao.springbootdemo.service;

import java.util.Set;

/**
 * @author futao
 * Created on 2019-03-14.
 */
public interface RedisService {
    Long listAdd(String key, String value);

    String listRemove(String key);

    Set<String> list();
}
