package com.futao.springbootdemo.model.system;

/**
 * 定义redis中的key值
 *
 * @author futao
 * Created on 2018-12-11.
 */
public class RedisKeySet {
    /**
     * 注册邮件验证码的key
     */
    public static final String REGISTER_EMAIL_CODE = "register:email:code";

    /**
     * mybatis的查询二级缓存key
     */
    public static final String MYBATIS_CACHE = "mybatis:cache";

    /**
     * rabbitmq发送消息失败的数据
     */
    public static final String RABBITMQ_SEND_FAIL = "rabbit:send:fail";

    /**
     * 生成redis中的key
     *
     * @param prefix 前缀
     * @param key    key
     * @return
     */
    public static String gen(String prefix, String key) {
        return prefix + ":" + key;
    }


}
