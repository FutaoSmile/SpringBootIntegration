package com.futao.springmvcdemo.foundation;

import com.futao.springmvcdemo.model.entity.Article;
import com.futao.springmvcdemo.model.entity.User;
import org.springframework.context.annotation.Bean;

/**
 * @author futao
 * Created on ${date}.
 */
public class EntityConfiguration {

    @Bean
    public User getUser() {
        System.out.println("user----");
        User user = new User();
        user.setAge("19");
        return user;
    }

    @Bean
    public Article getArticle() {
        System.out.println("article----");
        return new Article();
    }
}
