package com.futao.springmvcdemo.service.impl

import com.alibaba.fastjson.JSONObject
import com.futao.springmvcdemo.dao.ArticleDao
import com.futao.springmvcdemo.dao.impl.ArticleSearchDao
import com.futao.springmvcdemo.foundation.LogicException
import com.futao.springmvcdemo.model.entity.Article
import com.futao.springmvcdemo.model.entity.constvar.ErrorMessage
import com.futao.springmvcdemo.service.ArticleService
import com.futao.springmvcdemo.utils.currentTimeStamp
import com.futao.springmvcdemo.utils.getFieldName
import com.futao.springmvcdemo.utils.uuid
import org.apache.commons.lang3.StringUtils
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilders
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * @author futao
 * Created on 2018/10/20.
 */
@Service
open class ArticleServiceImpl : ArticleService {
    @Resource
    private lateinit var articleDao: ArticleDao
    @Resource
    private lateinit var redisTemplate: RedisTemplate<Any, Any>

    @Resource
    private lateinit var elasticsearch: ArticleSearchDao

    @Resource
    private lateinit var elastic: Client

    override fun add(title: String, desc: String, content: String) {
        if (articleDao.add(uuid(), title, desc, content, currentTimeStamp(), currentTimeStamp()) < 1) {
            throw LogicException.le(ErrorMessage.ADD_ARTICLE_FAIL)
        }
    }

    //    @Cacheable(value = ["article"])
    override fun list(): List<Article> {
        //        NativeSearchQueryBuilder()
//        elastic
//                .prepareSearch("futao")
//                .setTypes("article")
//                .setQuery(QueryBuilders.matchAllQuery()!!)
//                .execute()
//                .actionGet()
//                .hits
//                .getAt(0)
//                .sourceAsString
        val opsForValue = redisTemplate.opsForValue()
        return if (opsForValue.get("articlelist") != null && opsForValue.get("articlelist") == StringUtils.EMPTY) {
            val list = opsForValue.get("articlelist") as List<Article>
            elasticsearch.saveAll(list)
            list
        } else {
            val list = articleDao.list()
            opsForValue.set("articlelist", list, 1000 * 10)
            elasticsearch.saveAll(list)
            list
        }
    }

    /**
     * 全文检索
     */
    override fun search(key: String): ArrayList<Article> {
        val hits = elastic.prepareSearch("futao")
                .setTypes("article")
                .setQuery(
                        QueryBuilders
                                .boolQuery()
                                .should(QueryBuilders.matchQuery(Article::getContent.getFieldName(), key))
                                .should(QueryBuilders.matchQuery(Article::getTitle.getFieldName(), key))
                                .should(QueryBuilders.matchQuery(Article::getDescription.getFieldName(), key))

                )
                .execute()
                .actionGet()
                .hits
        val list: ArrayList<Article> = arrayListOf()
        hits.forEach { it -> list.add(JSONObject.parseObject(it.sourceAsString, Article::class.java)) }
        return list
    }
}