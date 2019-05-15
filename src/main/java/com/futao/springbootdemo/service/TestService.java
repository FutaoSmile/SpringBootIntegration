package com.futao.springbootdemo.service;

import com.futao.springbootdemo.model.entity.User;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public interface TestService {
    void redisHashTest(String name);

    /**
     * 查询
     *
     * @param name
     * @return
     */
    User redisHashSelect(String name);

    boolean notSelect4Update();

    void select4Update();

    void afterSelect();

    void sendMsgByRabbit(String routingKey, String msg);

    int transactionTest(int amount);

    void list();

    void batchInsert();

    void select();
}
