package com.futao.springmvcdemo.utils;

import java.util.*;

/**
 * url签名工具类
 *
 * @author futao
 * @version 1.0 at 2018年7月6日
 */
public class SignatureService {

    /**
     * url加密 注意：不包含sign
     *
     * @param packageParams 参数Map
     * @param appserect
     * @return
     */
    public static String signature(Map<String, String> packageParams, String appserect) {

        SortedMap<String, String> sortedMap = new TreeMap<>(packageParams);
        StringBuffer sb = new StringBuffer();
        Iterator<String> iterator = sortedMap.keySet().iterator();
        while (iterator.hasNext()) {
            String key = String.valueOf(iterator.next());
            String value = String.valueOf(packageParams.get(key));
            if (null != value && !"sign".equals(key) && !value.equals("null") && !"".equals(value)) {
                sb.append(key + value);
            }
        }
        String signString = sb.toString() + appserect;
        return CommonUtilsKt.md5(signString).toUpperCase();
    }

    public static boolean signatureVerify(Map<String, String> packageParams, String appserect) {

        return Objects.equals(packageParams.get("sign"), signature(packageParams, appserect));
    }
}
