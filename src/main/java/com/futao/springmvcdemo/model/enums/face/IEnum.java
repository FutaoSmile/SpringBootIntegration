package com.futao.springmvcdemo.model.enums.face;

/**
 * 【强制】项目中枚举类都需要实现的接口，目的是对字段进行hibernate validator校验
 *
 * @author futao
 * Created on 2019-02-22.
 */
public interface IEnum {
    /**
     * 获取状态，一般为Enum的key值
     *
     * @return
     */
    String getStatus();
}
