package com.futao.springmvcdemo.service;

import java.util.UUID;

/**
 * @author futao
 * Created on 2018/9/22-15:54.
 */
public interface UUIDService {
    /**
     * 获取一个32位的uuid
     *
     * @return
     */
    static String get() {
        return String.valueOf(UUID.randomUUID()).replace("-", "");
    }
}
