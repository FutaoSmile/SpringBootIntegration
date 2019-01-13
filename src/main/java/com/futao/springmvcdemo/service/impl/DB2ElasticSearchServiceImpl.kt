package com.futao.springmvcdemo.service.impl

import com.futao.springmvcdemo.dao.ArticleDao
import com.futao.springmvcdemo.foundation.LogicException
import com.futao.springmvcdemo.model.entity.BaseEntity
import com.futao.springmvcdemo.model.system.ErrorMessage
import com.futao.springmvcdemo.service.DB2ElasticSearchService
import org.reflections.Reflections
import org.slf4j.LoggerFactory
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * @author futao
 * Created on 2018/10/25.
 */
@Service
open class DB2ElasticSearchServiceImpl : DB2ElasticSearchService {

    private val logger = LoggerFactory.getLogger(DB2ElasticSearchServiceImpl::class.java)
    @Resource
    private lateinit var articleDao: ArticleDao
//    @Resource
//    private lateinit var elasricTemplate: ElasticsearchTemplate
//    @Resource
//    private lateinit var articleSearchDao: ArticleSearchDao

    override fun sync() {
        val reflections = Reflections("com.futao.springmvcdemo.model.entity")
        val classes = reflections.getTypesAnnotatedWith(Document::class.java)
        logger.info("数据库数据同步elasticsearch开始...")
        classes.forEach { it ->
            if (BaseEntity::class.java.isAssignableFrom(it)) {
                //数据同步
//                articleSearchDao.saveAll(articleDao.list())
            } else {
                throw LogicException.le(ErrorMessage.REBUILD_ELASTICSEARCH_FAIL_ENTITY_MUST_EXTENDS_BASE_ENTITY, arrayOf(it.name))
            }
        }


    }
}