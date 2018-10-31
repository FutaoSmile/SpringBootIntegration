package com.futao.springmvcdemo.service.impl

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * @author futao
 * Created on 2018/10/24.
 */
@Async
@Service
open class ScheduledServiceImpl {
    private val logger = LoggerFactory.getLogger(ScheduledServiceImpl::class.java)

//    @Scheduled(cron = "* * * * * ?")
    open fun test1() {
        logger.info("11")
    }

//    @Scheduled(cron = "* * * * * ?")
    open fun test2() {
        logger.info("22")
    }
}