package com.futao.springmvcdemo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.futao.springmvcdemo.model.entity.User;
import com.futao.springmvcdemo.model.system.RestResult;
import org.joda.time.DateTime;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

/**
 * @author futao
 * Created on 2018/9/18-10:37.
 */
public class NormalTest {

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
