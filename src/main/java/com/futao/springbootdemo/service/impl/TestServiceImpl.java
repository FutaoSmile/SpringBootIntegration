package com.futao.springbootdemo.service.impl;


import com.futao.springbootdemo.dao.TagDao;
import com.futao.springbootdemo.dao.TestDao;
import com.futao.springbootdemo.foundation.mq.rabbit.RabbitMqExchangeEnum;
import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.service.TestService;
import com.futao.springbootdemo.utils.CommonUtilsKt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;

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

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private Executor executor;

    @Resource
    private TagDao tagDao;

    @Async
//    @Scheduled(fixedRate = 1000L)
    public void async() {
        log.info(System.currentTimeMillis() + "");
    }

    //    @Scheduled(fixedRate = 1000L)
    public void async2() {
        executor.execute(() -> log.info(System.currentTimeMillis() + "=="));
    }

    @Override
    public void sendMsgByRabbit(String routingKey, String msg) {
        User user = new User();
        user.setId(CommonUtilsKt.uuid());
        user.setAddress("上海市中山公园");
        user.setUsername(msg);
        rabbitTemplate.convertAndSend(RabbitMqExchangeEnum.TOPIC_EXCHANGE_BAK.getExchangeName(), routingKey, user, new CorrelationData(user.getId()));
    }

    @Override
    public int transactionTest(int amount) {
        testDao.incrementA("1", amount);
        int a = 11 / (amount - 10);
        log.info(StringUtils.repeat("=", 60));
        return testDao.incrementB("1", -amount);
    }

    @Override
    public void list() {
        testDao.list();
        testDao.incrementA("1", 1);
        testDao.list();
        testDao.list();
    }


    @Override
    public void batchInsert() {
        Integer[] balances = new Integer[]{1, 12, 3, 12, 4, 4, 5, 46, 5, 76, 8, 7, 8, 111, 9, 9};
        jdbcTemplate.batchUpdate("insert into springmvcdemo.futao_test_a(`balance`) values(?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement, int i) throws SQLException {
                preparedStatement.setInt(1, balances[i]);
            }

            @Override
            public int getBatchSize() {
                return balances.length;
            }
        });
        jdbcTemplate.update("delete from springmvcdemo.futao_test_a where balance= ? ", 3);
        Set<String> strings = new HashSet<>();
        strings.add("1");
        strings.add("3");
        strings.add("2");


        MapSqlParameterSource parameters = new MapSqlParameterSource();
        parameters.addValue("ids", strings);
        namedParameterJdbcTemplate.update("delete from springmvcdemo.futao_test_01 where name in (:ids)", parameters);
//        jdbcTemplate.update("delete from springmvcdemo.futao_test_01 where name in (:ids)", parameters);
    }

    @Override
    public void select() {
        jdbcTemplate.query("select * from wlb_tag limit 1", (resultSet) -> {
            System.out.println(resultSet.getString("id"));
        });

    }
}
