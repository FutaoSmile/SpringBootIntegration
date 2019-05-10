package com.futao.springbootdemo.service.impl;

import com.futao.springbootdemo.foundation.mq.rabbit.RabbitMqExchangeEnum;
import com.futao.springbootdemo.model.entity.Tag;
import com.futao.springbootdemo.service.RabbitMqService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author futao
 * Created on 2019-05-10.
 */
@Slf4j
@Service
public class RabbitMqServiceImpl implements RabbitMqService {

    private final Executor executor;

    private final JdbcTemplate jdbcTemplate;

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqServiceImpl(Executor executor, JdbcTemplate jdbcTemplate, RabbitTemplate rabbitTemplate) {
        this.executor = executor;
        this.jdbcTemplate = jdbcTemplate;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void syncMsg2Mq() {
        AtomicInteger count = new AtomicInteger();
        jdbcTemplate.query("select count(*) from west_lake_blog.wlb_tag", rs -> {
            count.set(rs.getInt(1));
        });
        log.info("数据量大小为:[{}]", count.get());
        int start = 0, limit = 100;
        for (int i = 0; i < (count.get() / limit) + 1; i++) {
            start = i * limit;
            Object[] param = new Object[]{start, limit};
            RowMapper<Tag> rowMapper = new BeanPropertyRowMapper<>(Tag.class);
            List<Tag> list =
                    jdbcTemplate.query(
                            "select * from west_lake_blog.wlb_tag limit ?,?", param, rowMapper);
            //消息投递到mq
            list.forEach(it -> rabbitTemplate.convertAndSend(RabbitMqExchangeEnum.TOPIC_EXCHANGE_BAK.getExchangeName(), "topic.tag.test.bak", it, new CorrelationData(it.getId())));
        }
    }
}
