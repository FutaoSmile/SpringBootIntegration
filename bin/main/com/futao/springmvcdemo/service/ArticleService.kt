package com.futao.springmvcdemo.service

import com.futao.springmvcdemo.model.entity.Article

interface ArticleService {

    fun list(): List<Article>
    fun add(title: String, desc: String, content: String)
    fun search(key: String): ArrayList<Article>
}
