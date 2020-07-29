package com.futao.springbootdemo.service.impl

import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
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


    //    @Scheduled(cron = "0 0 0 * * ? ")
    open fun test3() {
        logger.info(StringUtils.repeat("--", 20))
    }

    //    @Scheduled(cron = "10,20,30,40,50 * * * * ? ")
    open fun test4() {
        logger.info(StringUtils.repeat("==", 20))
    }


    //    @Scheduled(cron = "* * * * * ? ")
    open fun test5() {
        logger.info(StringUtils.repeat("++", 20))
    }

    //    @Scheduled(cron = "0 47 13 ? * WED")
    open fun test6() {
        logger.info("Good" + StringUtils.repeat("====", 100))
    }
}