package com.futao.springbootdemo.smart4j.foundation;

import com.futao.springbootdemo.model.system.ErrorMessage;
import com.lazyer.foundation.foundation.exception.LogicException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

/**
 * 读取Properties文件工具类
 * <p>
 * ************加载properties文件的三种方式
 * 1.线程上下文类加载器getContextClassLoader()
 * 2.通过ClassPathLoader
 * 3.交给Spring--<context:property-placeholder location="classpath:xxx.properties">
 * *************************************
 * 线程上下文类加载器（context class loader）是从 JDK 1.2 开始引入的。
 * 类 java.lang.Thread中的方法 getContextClassLoader()和 setContextClassLoader(ClassLoader cl)用来获取和设置线程的上下文类加载器。
 * 如果没有通过 setContextClassLoader(ClassLoader cl)方法进行设置的话，线程将继承其父线程的上下文类加载器。
 * Java 应用运行的初始线程的上下文类加载器是系统类加载器。在线程中运行的代码可以通过此类加载器来加载类和资源。
 *
 * @author futao
 * Created on 2019-01-03.
 */
public class PropertiesUtils {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesUtils.class);

    /**
     * 配置文件集合
     */
    private static final HashMap<String, Properties> PROPERTIES_HASH_MAP = new HashMap<>();


    /**
     * 加载配置文件
     *
     * @param propertiesFileName 文件名
     */
    private static Properties loadProps(String propertiesFileName) {
        //未加载过才进行加载
        Properties propertiesFromMap = PROPERTIES_HASH_MAP.get(propertiesFileName);
        if (propertiesFromMap == null) {
            //或者使用ClassPathResource()
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName);
            //文件不存在
            if (inputStream == null) {
                throw LogicException.le(ErrorMessage.LogicErrorMessage.PROPERTIES_NOT_EXISTS, new Object[]{propertiesFileName});
            }
            try {
                Properties properties = new Properties();
                properties.load(inputStream);
                PROPERTIES_HASH_MAP.put(propertiesFileName, properties);
                return properties;
            } catch (IOException e) {
                e.printStackTrace();
                LOGGER.error("读取配置文件{0}发生异常:" + e.getMessage(), propertiesFileName, e);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    LOGGER.error("关闭输入流发生异常:" + e.getMessage(), e);
                }
            }
        }
        return propertiesFromMap;
    }

    /**
     * 读取配置文件
     * 目前测试下来，如果配置文件中名中包含数字则会加载失败
     *
     * @param propertiesFileName 文件名
     * @param key                读取的配置的key
     * @return key对应的值如果不存在则返回空字符串
     */
    public static String get(String propertiesFileName, String key) {
        Properties properties = loadProps(propertiesFileName);
        return properties.getProperty(key, StringUtils.EMPTY);
    }


}
