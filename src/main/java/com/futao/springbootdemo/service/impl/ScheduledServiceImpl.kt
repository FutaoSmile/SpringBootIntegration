package com.futao.springbootdemo.service.impl

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.stereotype.Service

/**
 * @author futao
 * Created on 2018/10/24.
 */
@Async
@Service
@EnableScheduling
open class ScheduledServiceImpl {
    private val logger = LoggerFactory.getLogger(ScheduledServiceImpl::class.java)

    //    @Scheduled(cron = "*/1 * * * * ?")
    open fun test1() {
        logger.info("11")
    }

    //    @Scheduled(cron = "*/2 * * * * ?")
    open fun test2() {
        logger.info("22")
    }
}