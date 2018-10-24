package com.futao.springmvcdemo.service

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

/**
 * @author futao
 * Created on 2018/10/24.
 */
@Async
@Service
open class SchedulServiceImpl {
    private val logger = LoggerFactory.getLogger(SchedulServiceImpl::class.java)

    @Scheduled(cron = "* * * * * ?")
    open fun test1() {
        logger.info("11")
    }

    @Scheduled(cron = "* * * * * ?")
    open fun test2() {
        logger.info("22")
    }
}