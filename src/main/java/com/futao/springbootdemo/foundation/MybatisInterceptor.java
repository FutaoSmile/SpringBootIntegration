package com.futao.springbootdemo.foundation;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * @author futao
 * Created on 2019-03-06.
 */
@Component
@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})})
public class MybatisInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisInterceptor.class);
    private static final String UPDATE = "update";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        //sql开始执行时间
        long startTime = System.currentTimeMillis();
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        if (UPDATE.equals(invocation.getMethod().getName())) {

        }
        Object proceed = invocation.proceed();
        //sql结束时间
        long endTime = System.currentTimeMillis();
        LOGGER.info("sql执行耗时{}毫秒", endTime - startTime);
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            RoutingStatementHandler handler = (RoutingStatementHandler) target;
            LOGGER.info("\n【sql: 】：{}\n【parameter: 】{}", handler.getBoundSql().getSql(), handler.getBoundSql().getParameterObject());
        }
        //把定义的插件注册进插件链里面 (越晚注册越先执行)
        return Plugin.wrap(target, this);
    }

    /**
     * 对属性进行配置
     *
     * @param properties
     */
    @Override
    public void setProperties(Properties properties) {

    }
}
