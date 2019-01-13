package com.futao.springmvcdemo.service

import com.futao.springmvcdemo.model.entity.Article

interface ArticleService {

    fun list(): List<Article>
    fun add(title: String, desc: String, content: String, visitTime: Int)
    fun search(key: String, fromRange: Int, toRange: Int, size: Int, from: Int): ArrayList<Article>
}
