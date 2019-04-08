package com.futao.springbootdemo.design.pattern.gof.a.singleton;

import org.apache.commons.lang3.StringUtils;

/**
 * 测试内部静态类的加载时机
 *
 * @author futao
 * Created on 2019-04-02.
 */
public class InnerStaticClassLoaderOrder {
    static {
        System.out.println("外部类被加载");
    }

    public void outerMethod() {
        System.out.println("调用外部类方法");
    }

    /**
     * 内部静态类
     */
    private static class InnerStaticClass {

        static int a;
        int b;

        static {
            System.out.println("内部类被加载了");
        }

        public void innerMethod() {
            System.out.println("调用静态类内部方法");
        }
    }

    /**
     * 内部类
     */
    private class InnerClass {
        //        static int a;//普通内部类不允许有静态成员
        int b;

        public void innerClassMethod() {
            System.out.println("println");
        }
    }

    public static void main(String[] args) {
        InnerStaticClassLoaderOrder i = new InnerStaticClassLoaderOrder();
        i.outerMethod();
        System.out.println(StringUtils.repeat("==", 30));
        //静态内部类通过new 外部类类名.内部类类名()的方式实例化对象
        InnerStaticClassLoaderOrder.InnerStaticClass innerStaticClass = new InnerStaticClassLoaderOrder.InnerStaticClass();
        innerStaticClass.innerMethod();

        /*
        输出为：外部类被加载
                调用外部类方法
                ============================================================
                内部类被加载了
                调用静态类内部方法


                说明内部静态类不会随着外部类的加载而加载，而是等到被实际调用的时候才加载
                         */

        InnerClass innerClass = i.new InnerClass();//普通内部类只能通过外部类对象.new 内部类()来实例化对象
        innerClass.innerClassMethod();
    }
}
