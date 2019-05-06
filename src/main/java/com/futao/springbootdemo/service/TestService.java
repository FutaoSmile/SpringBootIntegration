package com.futao.springbootdemo.service;

/**
 * @author futao
 * Created on 2019-04-10.
 */
public interface TestService {
    void sendMsgByRabbit(String msg);

    int transactionTest(int amount);

    void list();

    void batchInsert();
}
