package com.futao.springbootdemo.design.pattern.gof.singleton;

/**
 * 单例模式4-枚举式
 * 避免了反射与反序列化的漏洞
 * 但是没有懒加载的效果
 *
 * @author futao
 * Created on 2018-12-25.
 */
public enum SingletonEnum {

    /**
     * 这个枚举元素本身就是单例的
     */
    INSTANCE;

    private int field;

    /**
     * 枚举也可以有普通成员方法
     *
     * @param words
     */
    public void say(String words) {
        System.out.println(words);
    }

    public int getField() {
        return field;
    }

    public void setField(int field) {
        this.field = field;
    }}
