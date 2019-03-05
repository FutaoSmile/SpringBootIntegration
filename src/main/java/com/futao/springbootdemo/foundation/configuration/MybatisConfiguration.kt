package com.futao.springbootdemo.foundation.configuration

import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.TransactionManagementConfigurer
import javax.annotation.Resource
import javax.sql.DataSource


/**
 * Mybatis配置类
 * @author futao
 * Created on ${date}.
 *
 * springboot会自动加载spring.datasource.*相关配置
 * 数据源就会自动注入到sqlSessionFactory中
 * sqlSessionFactory会自动注入到Mapper中，对了你一切都不用管了，直接拿起来使用就行了。
 */
@Configuration
@EnableTransactionManagement//加上这个注解，使得支持事务
open class MybatisConfiguration : TransactionManagementConfigurer {

    @Resource
    lateinit var dataSource: DataSource

    /**
     * 实现接口 TransactionManagementConfigurer 方法，其返回值代表在拥有多个事务管理器的情况下默认使用的事务管理器
     */
    override fun annotationDrivenTransactionManager(): PlatformTransactionManager {
        return transactionManager()
    }


    @Bean(name = ["sqlSessionFactory"])
    open fun sqlSessionFactory(): SqlSessionFactoryBean {
        val factoryBean = SqlSessionFactoryBean()
        factoryBean.setDataSource(dataSource)
        return factoryBean
    }

    //    @Bean(name = ["transactionManager"])
    open fun transactionManager(): DataSourceTransactionManager {
        val transactionManager = DataSourceTransactionManager()
        transactionManager.dataSource = dataSource
        return transactionManager
    }


}