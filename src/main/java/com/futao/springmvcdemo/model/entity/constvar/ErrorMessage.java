package com.futao.springmvcdemo.model.entity.constvar;

/**
 * @author futao
 * Created on 2018/9/21-15:29.
 * 错误提示集合类
 * 错误码构成：   01程序员编号
 * 001该程序员定义的错误码
 * 后面再跟上错误信息
 */
public final class ErrorMessage {
    public static final String SYSTEM_EXCEPTION = "系统繁忙，请稍后再试";
    public static final String NOT_LOGIN = "00001_您还未登陆或者登陆已超时，请重新登陆";
    public static final String CONTAIN_ILLEGAL_PARAMETER = "00002_评论中包含敏感词汇";

    public static final String MOBILE_ALREADY_REGISTER = "01002_该手机号已经被注册了";
    public static final String LOGIC_EXCEPTION = "01003_对不起，你是真的没有我帅";
    public static final String MOBILE_LEN_ILLEGAL = "01004_手机号长度不合法";
    public static final String EMAIL_ILLEGAL = "01005_邮箱格式不合法";
    public static final String USERNAME_LEN_ILLEGAL = "01006_名字长度不合法";
    public static final String MOBILE_OR_PWD_ERROR = "01007_手机号或者密码错误，请重试";
    public static final String FIELD_NO_GETTER_OR_SETTER = "01008_该字段没有getter或者setter";
    public static final String DESERIALIZER_FAIL = "01009_反序列化失败";
}
