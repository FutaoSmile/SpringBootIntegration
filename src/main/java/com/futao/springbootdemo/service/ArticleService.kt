package com.futao.springbootdemo.service

import com.futao.springbootdemo.model.entity.Article
import com.futao.springbootdemo.model.entity.User

interface ArticleService {

    fun list(): List<Article>
    fun search(key: String, fromRange: Int, toRange: Int, size: Int, from: Int): ArrayList<Article>
    fun add(title: String, desc: String, content: String, user: User)
    fun getById(id: String): Article?
}
