package com.futao.springbootdemo.utils;

import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

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
        StringBuilder sb = new StringBuilder();
        for (String s : sortedMap.keySet()) {
            String key = String.valueOf(s);
            String value = String.valueOf(packageParams.get(key));
            if (null != value && !"sign".equals(key) && !"null".equals(value) && !"".equals(value)) {
                sb.append(key).append(value);
            }
        }
        String signString = sb.toString() + appserect;
        return Objects.requireNonNull(CommonUtilsKt.md5(signString)).toUpperCase();
    }

    public static boolean signatureVerify(Map<String, String> packageParams, String appserect) {

        return Objects.equals(packageParams.get("sign"), signature(packageParams, appserect));
    }
}
