package com.futao.springmvcdemo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.foundation.configuration.HttpMessageConverterConfiguration;
import com.futao.springmvcdemo.model.entity.ApiControllerDescription;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.enums.UserRoleEnum;
import com.futao.springmvcdemo.model.system.ErrorMessage;
import com.futao.springmvcdemo.model.system.RestResult;
import com.futao.springmvcdemo.smart4j.annotation.SmartService;
import com.futao.springmvcdemo.smart4j.foundation.ClassHelper;
import com.futao.springmvcdemo.smart4j.foundation.ClassUtils;
import com.futao.springmvcdemo.suit.A;
import com.futao.springmvcdemo.suit.B;
import com.futao.springmvcdemo.suit.CC;
import com.futao.springmvcdemo.utils.http.GetRequest;
import lombok.Getter;
import lombok.Setter;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.joda.time.DateTime;
import org.junit.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author futao
 * Created on 2018/9/18-10:37.
 */
public class NormalTest {
    @Test
    public void test50() {
        ArrayList<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");

        System.out.println(Arrays.toString(list.toArray(new String[0])));
        System.out.println(list);
    }

    @Test
    public void test49() {
//        GetRequest getRequest = new GetRequest("https://www.cnblogs.com/lingiu/p/3782813.html");
//        getRequest.addParameter("p1", "123");
//        getRequest.addParameter("p1", "123");
//        getRequest.addParameter("p2", "1233333");
//        System.out.println(getRequest.send());

//        String u = "http://localhost:8888/test";
//
//        PostRequest postRequest = new PostRequest(u + "/postObject");
//        postRequest.addHeader(HttpHeaders.ACCEPT_ENCODING, MediaType.APPLICATION_JSON_UTF8_VALUE);
//        postRequest.addParameter("p1", "江西");
//        User user = new User();
//        user.setUsername("qwaskdh");
//        postRequest.addEntity(user);
//        System.out.println(postRequest.send());
//
//        DeleteRequest deleteRequest = new DeleteRequest(u + "/deletePathVariable/"+000);
//        deleteRequest.addParameter("p1","123");
//        deleteRequest.send();


//        String url = "https://kyfw.12306.cn/otn/leftTicket/queryZ?leftTicketDTO.train_date=2019-02-10&leftTicketDTO.from_station=FCG&leftTicketDTO.to_station=HGH&purpose_codes=ADULT";
//        String result = new GetRequest(url).send();
        String url = "http://localhost:8888/test/get";

//        for (int i = 0; i < 5; i++) {
        GetRequest getRequest = new GetRequest(url);
        getRequest.addCredentials("123", "123");
        getRequest.addHeader("5", "6");
        getRequest.addCookie(new BasicClientCookie("coo", "12313"));
        getRequest.addParameter("p1", "123132");
        getRequest.addParameter("p2", "1231344");
        System.out.println(JSONObject.toJSONString(JSON.parseObject(getRequest.send()), HttpMessageConverterConfiguration.SERIALIZER_FEATURES));
//        }
    }

    @Test
    public void test48() {
        String a = "1234567";
        System.out.println(a.substring(4, 4));
    }

    @Test
    public void test47() {
        Integer a = 1;
        Integer b = Integer.parseInt("1");
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        System.out.println(a.equals(1));
        System.out.println(a.equals(map.get("1")));
        System.out.println(b.equals(1));
        System.out.println(b.equals(map.get("1")));
    }

    @Test
    public void test46() {
        List<String> list1 = new ArrayList<>();
        list1.add("1");
        list1.add("2");
        list1.add("3");
        List<String> list2 = new ArrayList<>();
        list2.add("1");
        list2.add("2");
        System.out.println(list1.stream().filter(l1 -> !list2.contains(l1)).collect(Collectors.toList()));

        System.out.println(list1.stream().filter(l1 -> list2.indexOf(l1) < 0).collect(Collectors.toList()));
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(NormalTest.class);

    @Test
    public void test45() {
        System.out.println(ClassHelper.getBeans());
        System.out.println(ClassHelper.getClassSetByAnn(SmartService.class));
    }


    @Test
    public void test44() {
        Set<Class<?>> set = ClassUtils.getClassSet("com.futao.springmvcdemo");
        set.forEach(System.out::println);
    }

    @Test
    public void test43() {
        //都是全部替换
        String a = "222";
        //可替换字符与字符串
        System.out.println(a.replace("2", "3"));
        //可写正则表达式
        System.out.println(a.replaceAll("2", "3"));
    }

    @Test
    public void test42() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        System.out.println(classLoader);
        ClassLoader classLoader1 = classLoader.getParent();
        System.out.println(classLoader1);
        ClassLoader loader = classLoader1.getParent();
        System.out.println(loader);
    }


    @Test
    public void test41() {
        String a = "12345";
        System.out.println(a.substring(2));     //345
        System.out.println(a.substring(2, 4));  //34
    }


    @Test
    public void test40() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date parse = simpleDateFormat.parse("2018-10-11");
        Timestamp timestamp = new Timestamp(parse.getTime());
        System.out.println(timestamp.toString());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(simpleDateFormat2.format(timestamp));
        System.out.println(new Date().getTime());
        System.out.println(new Timestamp(new Date().getTime()));
    }

    @Test
    public void test39() {
        User user = new User();
        user.setAge("18");
        System.out.println(JSONObject.toJSONString(user,
                SerializerFeature.PrettyFormat,
                SerializerFeature.SkipTransientField,
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue
        ));
        System.out.println(JSON.toJSON(user));
        System.out.println(JSON.toJSONString(user));
        ApiControllerDescription apiControllerDescription = new ApiControllerDescription("desc", new ArrayList<>(),
                "cn", new ArrayList<>());
    }

    @Test
    public void test38() {
        System.out.println(JSON.toJSONString(UserRoleEnum.ADMIN));
        System.out.println(JSON.toJSON(UserRoleEnum.ADMIN));
        System.out.println(UserRoleEnum.ADMIN.toString());
    }

    @Test
    public void test37() throws JsonProcessingException {
        com.futao.springmvcdemo.A a = new com.futao.springmvcdemo.A();
        a.add("super1");
        a.add("super2");
        ArrayList list = new ArrayList();
        list.add("sub1");
        list.add("sub2");
        a.setList(list);
        a.setString("aHa");
//        a.setList(new ArL);
        ObjectMapper jsonList = new ObjectMapper();
        System.out.println(a.toString());
        System.out.println(jsonList.writeValueAsString(a));
    }

    /**
     * 当前系统时间
     */
    @Test
    public void test36() {
        System.out.println(new Timestamp(new DateTime().getMillis()));
        System.out.println(new Timestamp(System.currentTimeMillis()));
        System.out.println(new Timestamp(new Date().getTime()));
        System.out.println(new Timestamp(Calendar.getInstance().getTimeInMillis()));
    }

    @Test
    public void test35() {
        String str = "";
        str.replaceAll("1", "2");
    }

    @Test
    public void test34() {
        System.out.println(true && true);
        System.out.println(true && false);
        System.out.println(false && true);
        System.out.println(false && false);
    }


    @Test
    public void test33() {
        DecimalFormat format = new DecimalFormat("0.00");

        System.out.println(format.format(Double.parseDouble("1")));
    }

    @Test
    public void test32() {
        JSONObject jsonObject = JSON.parseObject(null);
        JSONObject jsonObject1 = JSON.parseObject("");
        System.out.println(jsonObject1.isEmpty());
        System.out.println(jsonObject.isEmpty());
    }

    @Test
    public void test31() {
        List<String> list = new ArrayList<>();
        list.add("1");

        list.add("2");
        list.add("0");
        list.add("3");
        for (int i = 0; i < list.size(); i++) {
            try {
                System.out.println(5 / Integer.valueOf(list.get(i)));
            } catch (Exception e) {
                System.out.println(
                        e.getLocalizedMessage()
                );
                continue;
            }
        }
    }

    @Test
    public void test30() {
        String str = "99.0909";
        double parseDouble = Double.parseDouble(str);
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        System.out.println(decimalFormat.format(parseDouble));

    }


    @Test
    public void test29() {
        String a = "123";
        System.out.println(a.substring(0, a.length() - 2));
    }

    @Test
    public void test28() {


        try {
            throw new NullPointerException("kongzhicheng");
        } catch (Exception e) {
            LOGGER.error("发生了异常{},{}", 6666, 7777, e);
        }
    }

    @Test
    public void test27() {
        System.out.println("1\n" +
                "11\n" +
                "111\n" +
                "1111\n" +
                "11111\n" +
                "1111\n" +
                "111\n" +
                "11\n" +
                "1");

        LOGGER.error("1\n" +
                "11\n" +
                "111\n" +
                "1111\n" +
                "11111\n" +
                "1111\n" +
                "111\n" +
                "11\n" +
                "1");

        LOGGER.warn("1\n" +
                "11\n" +
                "111\n" +
                "1111\n" +
                "11111\n" +
                "1111\n" +
                "111\n" +
                "11\n" +
                "1");
    }

    @Test
    public void test26() {
        String jsonStr = "{\"a\":null}";
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        System.out.println(jsonObject.getJSONObject("a"));
        jsonObject.put("active", false);
        System.out.println(jsonObject);
        jsonObject.put("active", true);
        System.out.println(jsonObject);

    }


    @Test
    public void test25() {
        StringBuffer sb = new StringBuffer();
        String chines = "$%^&*()!@~#重庆123重量";


        char[] chars = chines.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] > 128) {
                try {
                    sb.append(PinyinHelper.toHanyuPinyinStringArray(chars[i], defaultFormat)[0].charAt(0));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                sb.append(chars[i]);
            }
        }

        System.out.println("sb : " + sb.toString());
    }

    @Test
    public void test24() {
        System.out.println(Arrays.asList(StringUtils.split(null, ",")));
    }

    @Test
    public void test23() {
        List<User> list1 = new ArrayList();
        List<User> list2 = new ArrayList();
        List<User> list3 = new ArrayList();

        List<String> names = new ArrayList<>();
        List<List<User>> users = new ArrayList<>();
        users.add(list1);
        users.add(list2);
        users.add(list3);
        System.out.println(users.stream().flatMap(Collection::stream));

    }

    @Test
    public void test22() {
    }

    @Test
    public void test21() {
//        DB2ElasticSearchServiceImpl db2ElasticSearchService = new DB2ElasticSearchServiceImpl();
//        db2ElasticSearchService.sync();
    }

    @Test
    public void test20() {
        throw LogicException.le(ErrorMessage.REBUILD_ELASTICSEARCH_FAIL_ENTITY_MUST_EXTENDS_BASE_ENTITY, "111");
    }

    @Test
    public void test19() {
        String a = null;
        t1(a);
    }

    public void t1(String str) {
        str.replace("1", "1");
    }

    @Test
    public void test18() {
        A a = new A();
        if (a instanceof B) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
        System.out.println(CC.class.isAssignableFrom(B.class));
    }

    /**
     * 获取类的信息
     */
    @Test
    public void test17() {
        Reflections reflections = new Reflections("com.futao.springmvcdemo.model.entity");
        Set<Class<?>> classSet = reflections.getTypesAnnotatedWith(Document.class);
        classSet.forEach(it -> System.out.println(it.getSimpleName()));
    }

    @Test
    public void test16() {
        Map<String, Integer> map = new HashMap<>();
        map.put("1", 1);
        map.put("2", 0);
        System.out.println(map.size());
    }


    /**
     * 如果类的属性没有getter的话toJson()得到的是空对象
     */
    @Test
    public void test14() {
        @Getter
        @Setter
        class A {
            private String a;
            private String c;

            public A(String a, String c) {
                this.a = a;
                this.c = c;
            }
        }
        List<A> list = new ArrayList<>();

//        list.add(new User("1231", "123123", "12", "1111", "1231", "12313"));
        list.add(new A("12312", "123123"));
        System.out.println(JSONObject.toJSON(list));
    }

    @Test
    public void test13() throws IllegalAccessException {
        ErrorMessage errorMessage = new ErrorMessage();
        final Class<? extends ErrorMessage> aClass = errorMessage.getClass();
        for (Field field : aClass.getFields()) {
            System.out.println(field.getName() + "___" + field.get(ErrorMessage.class));
        }
    }

    @Test
    public void test12() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < 12; i++) {
            list.add(i);
        }
        System.out.println("before: " + list);
        int size = list.size();
        System.out.println("after: " + list.subList(size - 10, size));
    }

    @Test
    public void test11() throws NoSuchMethodException {
        System.out.println(Arrays.toString(new Method[]{User.class.getMethod("getAddress")}));
    }

    /**
     * 0.1+0.2=0.300000000004问题
     * 解决方案是转为BigDecimal或者放大倍数为整数进行计算
     */
    @Test
    public void test9() {
        System.out.println(0.1 + 0.2);
        System.out.println(BigDecimal.valueOf(0.1).add(BigDecimal.valueOf(0.2)));
        System.out.println((0.1 * 10 + 0.2 * 10) / 10);
        System.out.println(0.3 - 0.1);
    }

    @Test
    public void test8() {
        DateTime now = DateTime.now();
        System.out.println(now.minus(now.minusMinutes(1).getMillis()));
    }

    @Test
    public void test7() {
        //取得系统已经安装的语言数组
        Locale[] locales = Locale.getAvailableLocales();
        //循环获取系统已经安装的国家和对应的语言代码
        for (Locale locale : locales) {
            System.out.println(locale.getDisplayCountry() + locale.getCountry() + "-" + locale.getDisplayLanguage() + locale.getLanguage());
        }
    }

    @Test
    public void test6() {
        RestResult restResult = new RestResult();
        User user = new User();
        user.setUsername("NiuBist");
        user.setAge("18");
        user.setEmail("12312");
        user.setMobile("12312321312");
        restResult.setData((Object) user);
        System.out.println("-" + JSONObject.toJSON(restResult));
        System.out.println("--" + JSONObject.toJSONString(restResult, SerializerFeature.PrettyFormat));
        System.out.println("---" + JSONObject.toJSON(restResult));
    }

    @Test
    public void test5() {
        String[] arr = new String[]{"123", "2312", "12321"};
        test6(arr);
    }

    public void test6(String... str) {
        System.out.println(Arrays.toString(str));
    }

    @Test
    public void test4() throws UnsupportedEncodingException {
        String originStr = "你好";
        System.out.println(new String(originStr.getBytes("UTF-8"), "UTF-8"));

    }

    @Test
    public void test3() {
        System.out.println(String.valueOf(UUID.randomUUID()).replace("-", "").length());
        User user = new User();
        user.setId("1212321as");
        user.setUsername("asjkh");
    }

    @Test
    public void test2() {
        System.out.println(String.valueOf(UUID.randomUUID()));
        JSONObject jsonObject = new JSONObject();
        String a = "{'1':12}";
        System.out.println(JSONObject.parseObject(a).isEmpty());

        LOGGER.info("----");
        LOGGER.debug("-----");
        LOGGER.error("-----");
        LOGGER.warn("-----");
    }

    @Test
    public void test1() {
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("a", "c");
        jsonArray.add(jsonObject);
        System.out.println(jsonArray);

        jsonObject.put("a", null);
        System.out.println(jsonArray);
    }

    @Test
    public void test() {
        String a = "";
        System.out.println(a.toString());
        System.out.println((String) a);
        System.out.println(String.valueOf(a));
    }
}
