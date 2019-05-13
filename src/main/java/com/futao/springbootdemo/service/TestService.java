package com.futao.springbootdemo.service;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public interface TestService {
    boolean notSelect4Update();

    void select4Update();

    void afterSelect();

    void sendMsgByRabbit(String routingKey, String msg);

    int transactionTest(int amount);

    void list();

    void batchInsert();

    void select();
}
