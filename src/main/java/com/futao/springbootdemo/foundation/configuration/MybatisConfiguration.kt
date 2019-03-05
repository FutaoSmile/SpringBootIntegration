package com.futao.springbootdemo.foundation.configuration

import org.mybatis.spring.SqlSessionFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.core.io.support.ResourcePatternResolver
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.transaction.annotation.TransactionManagementConfigurer
import java.util.*
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
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        // 设置mybatis configuration 扫描路径
        sqlSessionFactoryBean.setConfigLocation(ClassPathResource("/mybatis/mybatis-config.xml"))
        // 添加mapper 扫描路径
        val pathMatchingResourcePatternResolver = PathMatchingResourcePatternResolver()
        val packageSearchPath = ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + "/mybatis/mapper/*.xml"
        sqlSessionFactoryBean.setMapperLocations(pathMatchingResourcePatternResolver.getResources(packageSearchPath))
        // 设置typeAlias 包扫描路径
        sqlSessionFactoryBean.setTypeAliasesPackage("com.futao.springbootdemo.model.entity")
        val properties = Properties()
        properties["logImpl"] = "org.apache.ibatis.logging.stdout.StdOutImpl"
        sqlSessionFactoryBean.setConfigurationProperties(properties)
        return sqlSessionFactoryBean
    }

    //    @Bean(name = ["transactionManager"])
    open fun transactionManager(): DataSourceTransactionManager {
        val transactionManager = DataSourceTransactionManager()
        transactionManager.dataSource = dataSource
        return transactionManager
    }


}