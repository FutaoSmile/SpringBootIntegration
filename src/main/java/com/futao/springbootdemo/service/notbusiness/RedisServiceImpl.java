package com.futao.springbootdemo.service.notbusiness;

import com.futao.springbootdemo.model.system.SystemConfig;
import com.futao.springbootdemo.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author futao
 * Created on 2019-03-14.
 */
@Service
@Transactional(isolation = Isolation.DEFAULT, timeout = SystemConfig.SERVICE_TRANSACTION_TIMEOUT_SECOND, rollbackFor = Exception.class)
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public Long listAdd(String key, String value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    @Override
    public String listRemove(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    @Override
    public Set<String> list() {
        return redisTemplate.opsForList().getOperations().keys("*");
    }
}
