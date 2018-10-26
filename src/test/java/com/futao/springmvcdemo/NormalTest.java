package com.futao.springmvcdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.futao.springmvcdemo.a.Classes;
import com.futao.springmvcdemo.a.Student;
import com.futao.springmvcdemo.foundation.LogicException;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage;
import com.futao.springmvcdemo.model.system.RestResult;
import com.futao.springmvcdemo.service.impl.DB2ElasticSearchServiceImpl;
import com.futao.springmvcdemo.suit.A;
import com.futao.springmvcdemo.suit.B;
import com.futao.springmvcdemo.suit.CC;
import lombok.Getter;
import lombok.Setter;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author futao
 * Created on 2018/9/18-10:37.
 */
public class NormalTest {

    @Test
    public void test22() {
    }

    @Test
    public void test21() {
        DB2ElasticSearchServiceImpl db2ElasticSearchService = new DB2ElasticSearchServiceImpl();
        db2ElasticSearchService.sync();
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


    @Test
    public void test15() {
        List<Classes> classesList = new ArrayList<>();

        Student s1 = new Student(55);
        Student s2 = new Student(66);
        Student s3 = new Student(77);

        List<Student> stuL1 = new ArrayList<>();
        stuL1.add(s1);
        stuL1.add(s2);
        List<Student> stuL2 = new ArrayList<>();
        stuL2.add(s3);

        Classes c1 = new Classes("架构1班", stuL1);
        Classes c2 = new Classes("架构2班", stuL2);


        classesList.add(c1);
        classesList.add(c2);

        System.out.println(classesList.stream().flatMap(it -> it.getStudents().stream().filter(stu -> stu.getScore() > 60)).collect(Collectors.toList()));
    }

    public void a(String... args) {
        for (String arg : args) {
            System.out.println(arg);
        }
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

    @Test
    public void test10() {
        ThreadLocal<User> threadLocal = new ThreadLocal<>();
        User user = new User();
        user.setAge("11");
        threadLocal.set(user);
        System.out.println(threadLocal.get());

        User user1 = new User();
        user1.setAddress("1231");
        threadLocal.set(user1);
        System.out.println(threadLocal.get());
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

    private static final Logger logger = LoggerFactory.getLogger(NormalTest.class);

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

        logger.info("----");
        logger.debug("-----");
        logger.error("-----");
        logger.warn("-----");
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
