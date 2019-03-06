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
    public static final String REGISTER_EMAIL_CODE = "REGISTER_EMAIL_CODE";

    /**
     * 生成redis中的key
     *
     * @param prefix
     * @param key
     * @return
     */
    public static String gen(String prefix, String key) {
        return prefix + ":" + key;
    }


}
