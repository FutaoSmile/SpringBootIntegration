package com.futao.springbootdemo.service.impl

import com.alibaba.fastjson.JSONObject
import com.futao.springbootdemo.dao.ArticleDao
import com.futao.springbootdemo.foundation.configuration.HibernateValidatorConfiguration
import com.futao.springbootdemo.model.entity.Article
import com.futao.springbootdemo.model.entity.User
import com.futao.springbootdemo.service.ArticleService
import com.futao.springbootdemo.service.UserService
import com.futao.springbootdemo.utils.ServiceTools
import com.futao.springbootdemo.utils.getFieldName
import com.futao.springbootdemo.utils.setCreateAndLastModifyTimeNow
import org.apache.commons.lang3.StringUtils
import org.elasticsearch.client.Client
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder
import org.elasticsearch.search.sort.SortOrder
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

//    @Resource
//    private lateinit var elasticsearch: ArticleSearchDao

    @Resource
    private lateinit var elastic: Client
    @Resource
    private lateinit var userService: UserService

    override fun add(title: String, desc: String, content: String, user: User): Article {
//        if (articleDao.add(uuid(), title, desc, content, currentTimeStamp(), currentTimeStamp()) < 1) {
//            throw LogicException.ae(ErrorMessage.ADD_ARTICLE_FAIL)
//        }
//        elasticsearch.save(Article().apply {
//            id = UUIDService.get()
//            setTitle(title)
//            description = desc
//            setContent(content)
//        }.setCreateAndLastModifyTimeNow())
        val article = Article(title, desc, content, userService.currentLoginUser(), 0, null).setCreateAndLastModifyTimeNow()
        HibernateValidatorConfiguration.validate(article)
        articleDao.add(article)
        return article
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
        return if (opsForValue.get("articlelist") != null && opsForValue.get("articlelist") != StringUtils.EMPTY) {
            val list = opsForValue.get("articlelist") as List<Article>
//            elasticsearch.saveAll(list)
            list
        } else {
            val list = articleDao.list()
            opsForValue.set("articlelist", list)
//            elasticsearch.saveAll(list)
            list
        }
    }

    /**
     * 全文检索
     * 全文索引会将输入的字符串根据语法(分词器)拆解开来，然后再到倒排索引去一一匹配，只要匹配到拆解之后的任意一个单词就可以返回该Document
     * 短语搜索phrase search要求输入的字符串必须匹配，不进行分词
     */
    override fun search(key: String, fromRange: Int, toRange: Int, size: Int, from: Int): ArrayList<Article> {
        val hits = elastic
                //查询的Index
                .prepareSearch(Article.ES_INDEX_NAME)
                //查询的Type
                .setTypes(Article.ES_TYPE)
                //关键字匹配搜索
                .setQuery(
                        QueryBuilders
                                .boolQuery()
                                .should(QueryBuilders.matchQuery(Article::getContent.getFieldName(), key).boost(1f))//权重1f
                                .should(QueryBuilders.matchQuery(Article::getTitle.getFieldName(), key).boost(2f))
                                .should(QueryBuilders.matchQuery(Article::getDescription.getFieldName(), key).boost(4f))

                )
                //范围搜索
                .setQuery(QueryBuilders.rangeQuery(Article::getVisitTimes.getFieldName()).from(fromRange).to(toRange))
                //高亮
                .highlighter(HighlightBuilder()
                        .highlightFilter(true)
                        .preTags("<em>")
                        .postTags("</em>")
                        .field(Article::getTitle.getFieldName())
                        .field(Article::getContent.getFieldName())
                )
                //Filter
//                .setPostFilter(QueryBuilders.boolQuery())
                //结果排序
                .addSort(Article::getCreateTime.getFieldName(), SortOrder.DESC)
                //分页开始
                .setFrom(from)
                //分页大小
                .setSize(size)
                .execute()
                .actionGet()
//                .get()
//                =.execute()
//                .actionGet()

                //TODO("分组查询")某个标签下的数量
                //TODO("平均价格")
                .hits
        val list: ArrayList<Article> = arrayListOf()
        //总数据量
        println("getTotalHits========" + hits.getTotalHits())
        hits.forEach { it ->
            run {
                val article = JSONObject.parseObject(it.sourceAsString, Article::class.java)
                article.title = it.highlightFields[Article::getTitle.getFieldName()]!!.fragments()[0].toString()
                article.content = it.highlightFields[Article::getContent.getFieldName()]!!.fragments()[0].toString()
                list.add(article)
            }
        }
        return list
    }

    override fun byId(id: String): Article? {
        return ServiceTools.checkResultNullAndThrow(articleDao.getById(id))
    }

    override fun my(): MutableList<Article>? {
        return articleDao.byUser(userService.currentLoginUser())
    }

    override fun update(id: String): Article {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(id: String): Article {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}