package com.futao.springbootdemo.foundation;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;

import java.util.List;
import java.util.Properties;

/**
 * Mybatis对象工厂-用于根据查询结果实例化出对应的java对象
 *
 * @author futao
 * Created on 2019-04-10.
 */
@Slf4j
public class MybatisObjectFactory extends DefaultObjectFactory {
    @Override
    public void setProperties(Properties properties) {
        log.info(">>> load setProperties:{}", properties);
        super.setProperties(properties);
    }

    @Override
    public <T> T create(Class<T> type) {
        return super.create(type);
    }

    @Override
    public <T> T create(Class<T> type, List<Class<?>> constructorArgTypes, List<Object> constructorArgs) {
        return super.create(type, constructorArgTypes, constructorArgs);
    }

    @Override
    public <T> boolean isCollection(Class<T> type) {
        return super.isCollection(type);
    }
}
