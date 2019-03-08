package com.futao.springbootdemo.service

import com.futao.springbootdemo.model.entity.Article
import com.futao.springbootdemo.model.entity.User

interface ArticleService {

    fun list(): List<Article>
    fun search(key: String, fromRange: Int, toRange: Int, size: Int, from: Int): ArrayList<Article>
    fun add(title: String, desc: String, content: String, user: User): Article
    fun byId(id: String): Article?
    fun my(): MutableList<Article>?
    fun update(id: String): Article
    fun delete(id: String): Article
}
