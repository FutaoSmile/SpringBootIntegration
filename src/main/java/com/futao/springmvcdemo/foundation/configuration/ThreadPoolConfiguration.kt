package com.futao.springmvcdemo.foundation.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

/**
 * @author futao
 * Created on 2018/10/24.
 * 线程池配置
 */
@Configuration
@EnableAsync
open class ThreadPoolConfiguration : AsyncConfigurer {

    @Value("\${threadpool.corePoolSize}")
    private var corePoolSize: Int = 0

    @Value("\${threadpool.corePoolSize}")
    private var maxPoolSize: Int = 0

    @Value("\${threadpool.corePoolSize}")
    private var queueCapacity: Int = 0

//    @Bean
//    open fun taskExecutor(): Executor {
//        val executor = ThreadPoolTaskExecutor()
//        executor.setQueueCapacity(queueCapacity)
//        executor.corePoolSize = corePoolSize
//        executor.maxPoolSize = maxPoolSize
//        executor.initialize()
//        return executor
//    }

    /**
     * The [Executor] instance to be used when processing async
     * method invocations.
     */
    override fun getAsyncExecutor(): Executor {
        val executor = ThreadPoolTaskExecutor()
        executor.setQueueCapacity(queueCapacity)
        executor.corePoolSize = corePoolSize
        executor.maxPoolSize = maxPoolSize
        executor.initialize()
        return executor
    }
}