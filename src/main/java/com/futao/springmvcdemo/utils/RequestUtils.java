package com.futao.springmvcdemo.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.ObjectUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author futao
 * Created on 2018/9/20-13:07.
 * HttpServletRequest请求工具类
 */
public final class RequestUtils {

    /**
     * 将cookie数组转换成jsonString
     *
     * @param cookies
     * @return
     */
    public static String getCookies(Cookie[] cookies) {
        JSONObject jsonObject = new JSONObject();
        if (ObjectUtils.allNotNull(cookies)) {
            for (Cookie cookie : cookies) {
                jsonObject.put(cookie.getName(), cookie.getValue());
            }
        }
        return JSON.toJSONString(jsonObject);
    }

    /**
     * 将session转换成jsonString
     *
     * @param session
     * @return
     */
    public static String getSessionParameters(HttpSession session) {
        JSONObject jsonObject = new JSONObject();
        if (ObjectUtils.allNotNull(session)) {
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String element = attributeNames.nextElement();
                jsonObject.put(element, session.getAttribute(element));
            }
        }
        return JSON.toJSONString(jsonObject);
    }
}
