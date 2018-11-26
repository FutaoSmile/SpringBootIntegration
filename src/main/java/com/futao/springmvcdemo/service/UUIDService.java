package com.futao.springmvcdemo.service;

import java.util.UUID;

/**
 * @author futao
 * Created on 2018/9/22-15:54.
 * <p>
 * Java 8 对接口做了进一步的增强。
 * <p>
 * a. 在接口中可以添加使用 default 关键字修饰的非抽象方法。即：默认方法（或扩展方法）
 * <p>
 * b. 接口里可以声明静态方法，并且可以实现。
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
