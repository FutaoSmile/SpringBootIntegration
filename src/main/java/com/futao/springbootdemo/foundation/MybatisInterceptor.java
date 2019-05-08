package com.futao.springbootdemo.foundation;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;

/**
 * mybatis拦截器
 * TODO("通过拦截器设置createTime lastModifyTime")
 *
 * @author futao
 * Created on 2019-03-06.
 */
@Intercepts(value = {
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisInterceptor implements Interceptor {

    /**
     * 慢sql时长
     */
    private static final long SLOW_SQL_TIME_MILLS = 1000L;

    private static final Logger LOGGER = LoggerFactory.getLogger(MybatisInterceptor.class);
    private static final String UPDATE = "update";

    @Resource
    private java.util.concurrent.Executor execute;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //sql开始执行时间
        long startTime = System.currentTimeMillis();
        //执行sql(事物超时：在事物提交之前超时)
        Object proceed = invocation.proceed();
        //事物提交之后再超时不会有异常抛出
        //sql结束时间
        long endTime = System.currentTimeMillis();
        //sql执行时间
        long sqlTime = endTime - startTime;
        if (true) {
            try {
                Object[] args = invocation.getArgs();
                MappedStatement ms = (MappedStatement) args[0];
                //对应的sql语句的id-全路径
                String id = ms.getId();
                // BoundSql就是封装 MyBatis最终产生的 sql类.args[1]是参数
                BoundSql boundSql = ms.getBoundSql(args[1]);
                // 获取节点的配置
                Configuration configuration = ms.getConfiguration();
                // 记录日志
                logSql(id, configuration, boundSql, sqlTime + "");
                //开启新线程记录慢sql
                if (sqlTime > SLOW_SQL_TIME_MILLS) {
                    SlowSql slowSql = new SlowSql(sqlTime);
                    execute.execute(slowSql);
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
        return proceed;
    }

    @Override
    public Object plugin(Object target) {
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

    /**
     * 记录sql
     *
     * @param id            sqlId
     * @param configuration 获取节点的配置
     * @param boundSql      sql
     * @param time          耗时
     */
    private void logSql(String id, Configuration configuration, BoundSql boundSql, String time) {
        String sql;
        //将换行符用空格代替
        sql = boundSql.getSql().replaceAll("\\n", " ");
        //将多个空格用一个空格代替
        sql = sql.replaceAll(" +", " ");
        //获取输入的参数
        Object parameterObject = boundSql.getParameterObject();
        //获取参数名称
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
            // 获取类型处理器注册器，类型处理器的功能是进行java类型和数据库类型的转换　　　　　　
            // 如果根据 parameterObject.getClass(）可以找到对应的类型，则替换
            TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
            if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                sql = sql.replaceFirst("\\?", parameterObject.toString());
            } else {
                // MetaObject主要是封装了 originalObject对象，提供了 get和 set的方法用于获取和设置 originalObject的属性值
                // 主要支持对 JavaBean、Collection、Map三种类型对象的操作
                MetaObject metaObject = configuration.newMetaObject(parameterObject);
                for (ParameterMapping parameterMapping : parameterMappings) {
                    //获取到参数名
                    String propertyName = parameterMapping.getProperty();
                    if (metaObject.hasGetter(propertyName)) {
                        sql = sql.replaceFirst("\\?", String.format("'%s'(%s)", metaObject.getValue(propertyName), metaObject.getGetterType(propertyName)));
                    } else if (boundSql.hasAdditionalParameter(propertyName)) {
                        Object obj = boundSql.getAdditionalParameter(propertyName);
                        // 该分支是动态 sql??
                        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(obj.toString()));
                    } else {
                        //??
                        sql = sql.replaceFirst("\\?", "缺失");
                    }

                }
            }

        }

        LOGGER.info("\n>>>【sql id: 】{}\n>>>【sql 语句: 】{}\n>>>【sql 耗时: 】{} ms", id, sql, time);
    }

    /**
     * 记录慢sql
     */
    class SlowSql implements Runnable {
        @Override
        public void run() {
            log();
        }

        private long sqlTime;

        SlowSql(long sqlTime) {
            this.sqlTime = sqlTime;
        }

        /**
         * 记录慢sql日志
         */
        private void log() {
            LOGGER.warn(StringUtils.repeat("-", 50) + "太慢了{}", sqlTime);
        }
    }
}
