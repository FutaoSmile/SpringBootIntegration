package com.futao.springbootdemo.foundation;

import com.futao.springbootdemo.model.entity.Article;
import com.futao.springbootdemo.model.entity.User;

/**
 * @author futao
 * Created on ${date}.
 */
public class EntityConfiguration {

    //    @Bean
    public User getUser() {
        System.out.println("user----");
        User user = new User();
        user.setAge("19");
        return user;
    }

    //    @Bean
    public Article getArticle() {
        System.out.println("article----");
        return new Article();
    }
}
