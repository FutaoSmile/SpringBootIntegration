package com.futao.springbootdemo.service.impl;


import com.futao.springbootdemo.dao.TestDao;
import com.futao.springbootdemo.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author futao
 * Created on 2019-04-10.
 */
@Slf4j
@Service
@Transactional(timeout = 3, rollbackFor = Exception.class)
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    @Override
    public int transactionTest(int amount) {
        testDao.incrementA("1", amount);
        int a = 11 / (amount - 10);
        log.info(StringUtils.repeat("=", 60));
        return testDao.incrementB("1", -amount);
    }
}
