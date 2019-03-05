package com.futao.springbootdemo.service.impl

import com.google.common.util.concurrent.RateLimiter
import org.springframework.stereotype.Service

/**
 * @author futao
 * Created on 2018/10/25.
 * 接口限流-令牌桶算法
 */
@Service
class AccessLimitServiceImpl {
    private val limiter = RateLimiter.create(1.0)

    /**
     * 获取令牌
     */
    fun getPermit(): Boolean {
        return limiter.tryAcquire()
    }

}