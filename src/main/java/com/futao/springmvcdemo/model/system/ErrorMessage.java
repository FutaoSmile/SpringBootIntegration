package com.futao.springmvcdemo.model.system;

/**
 * @author futao
 * Created on 2018/9/21-15:29.
 * 错误提示集合类
 * 错误码构成：   01程序员编号
 * 001该程序员定义的错误码
 * 后面再跟上错误信息
 */
public final class ErrorMessage {

    public static final String SEPARATOR = "_";

    public static class ApplicationException {
        public static final String SYSTEM_EXCEPTION = "系统繁忙，请稍后再试";
        public static final String INIT_OBJ_FAIL = "实例化%s失败";
        public static final String EXPORT_EXCEL_FAIL = "导出excel时发生异常";
        public static final String CLOSE_OUTPUT_STREAM_FAIL = "关闭输出流时发生异常";
        public static final String INVOKE_METHOD_FAIL = "执行方法时发生异常";
        public static final String ENUM_MUST_IMPL_IENUM = "使用@EnumStatus(EnumClass.class)注解之前，您的枚举类EnumClass必须实现IEnum接口";
        public static final String DATE_PARSE_FAIL = "时间转换异常";
    }

    /**
     * 命名规范：操作对象_属性_描述
     * 例如:USER_SEX_ENUM_ILLEGAL
     * USER_MOBILE_LEN_TOO_LONG
     */
    public static class LogicErrorMessage {
        public static final String NOT_LOGIN = "00001_您还未登陆或者登陆已超时，请重新登陆";
        public static final String CONTAIN_ILLEGAL_PARAMETER = "00002_评论中包含敏感词汇";
        public static final String ROLE_NOT_EXIST = "00003_角色信息不存在";
        public static final String ROLE_NOT_ALLOW = "00004_您当前的角色无此权限";
        public static final String PROPERTIES_NOT_EXISTS = "00005_配置文件%s不存在";
        public static final String VISIT_TOO_FREQUENTLY = "00006_访问太频繁,请稍后再试";
        public static final String I18N_RESOURCE_NOT_FOUND = "00007_获取国际化资源%s失败,请检查您的配置";
        public static final String GEN_URI_FAIL = "00007_构造请求地址时发生异常";
        public static final String REQUEST_METHOD_NOT_EXISTS = "00008_请求方式不存在";
        public static final String REQUEST_FAIL = "00008_请求时发生异常%s";
        public static final String GET_RESPONSE_FAIL = "00009_获取响应失败%s";
        public static final String CLOSE_RESPONSE_FAIL = "00010_关闭response失败%s";
        public static final String ADD_AUTH_HEAD_FAIL = "00011_request authentication fail %s";


        public static final String FIELD_NO_GETTER_OR_SETTER = "01008_该字段没有getter或者setter";
        public static final String DESERIALIZER_FAIL = "01009_反序列化失败";
        public static final String ROCKET_MQ_PRODUCER_GROUP_NAME_EMPTY = "01010_rocketMq的producerGroupName不允许为空";
        public static final String ROCKET_MQ_PRODUCER_NAME_SERVER_EMPTY = "01010_rocketMq的producerNameServer不允许为空";
        public static final String ROCKET_MQ_CONSUMER_GROUP_NAME_EMPTY = "01010_rocketMq的consumerGroupName不允许为空";
        public static final String ROCKET_MQ_CONSUMER_NAME_SERVER_EMPTY = "01010_rocketMq的consumerNameServer不允许为空";
        public static final String ROCKET_MQ_CONSUMER_TOPICS_EMPTY = "01010_rocketMq的topics不允许为空";


        public static final String MOBILE_ALREADY_REGISTER = "01002_该手机号已经被注册了";
        public static final String LOGIC_EXCEPTION = "01003_对不起，你是真的没有我帅";
        public static final String MOBILE_LEN_ILLEGAL = "01004_手机号长度不合法";
        public static final String EMAIL_ILLEGAL = "01005_邮箱格式不合法";
        public static final String USERNAME_LEN_ILLEGAL = "01006_名字长度不合法";
        public static final String MOBILE_OR_PWD_ERROR = "01007_手机号或者密码错误，请重试";
        public static final String PASSWORD_LEN = "01008_密码长度最少8位";

        public static final String ADD_ARTICLE_FAIL = "01011_新增文章失败";

        public static final String REBUILD_ELASTICSEARCH_FAIL_ENTITY_MUST_EXTENDS_BASE_ENTITY = "01012_重建elasticsearch索引失败，实体%s必须继承BaseEntity";
        public static final String PARSE_TO_DOUBLE_FAIL = "01013_输入的字符串不可转为数值";

        public static final String EMAIL_ALREADY_EXIST = "01014_该邮箱已被注册，请直接登录或找回密码";
        public static final String EMAIL_ALREADY_SEND = "01015_邮件已发送，请检查您的邮箱";
        public static final String AGE_ERROR = "01016_年龄数据错误";
        public static final String ADDRESS_CAN_NOT_BE_EMPTY = "01017_地址不可为空";
        public static final String PASSWORD_CAN_NOT_BE_EMPTY = "01018_密码不可为空";
        public static final String ADDRESS_LEN_TOO_LARGE = "01019_地址过长";
        public static final String VERIFY_CODE_EXPIRED = "01020_验证码已过期";
        public static final String VERIFY_CODE_ERROR = "01021_验证码不正确";
        public static final String ENUM_NOT_ALLOW = "01022_不是合法的枚举类型";

        public static final String USER_SEX_ENUM_ILLEGAL = "01023_用户性别不合法";
        public static final String USER_STATUS_ENUM_ILLEGAL = "01023_用户状态不合法";
        public static final String USER_ROLE_ENUM_ILLEGAL = "01023_用户角色不合法";


    }
}
