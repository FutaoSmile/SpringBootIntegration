package com.futao.springbootdemo.dao.sql.provider;

import com.futao.springbootdemo.model.entity.User;
import com.futao.springbootdemo.utils.CommonUtilsKt;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author futao
 * Created on 2018-12-13.
 */
public class UserProvider {

    /**
     * new XXX(){}表示声明一个继承自XXX(可以是类，可以是接口)的匿名类对象，同时在匿名类中可以重写基类的方法。
     *
     * @return
     */
    public String list() {
        return new SQL() {
            {
                SELECT("*");
                FROM(CommonUtilsKt.tablePrefix + User.class.getSimpleName());
//                if ()
            }

        }.toString();
    }

}
