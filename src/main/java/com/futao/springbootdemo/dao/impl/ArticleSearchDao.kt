package com.futao.springbootdemo.dao.impl

import com.futao.springbootdemo.model.entity.Article
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository

/**
 * @author futao
 * Created on 2018/10/22.
 */
interface ArticleSearchDao : ElasticsearchRepository<Article, String> {

    /**
     * springboot封装的CrudRepository接口
     * 可以简化某些开发，比如findByFieldName
     * 直接定义这样的方法就可以直接使用了，ElasticsearchRepository的具体实现类是`SimpleElasticsearchRepository`
     */
    fun findByTitle(title: String): List<Article>

}