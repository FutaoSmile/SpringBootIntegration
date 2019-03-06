package com.futao.springbootdemo.service.impl

import com.futao.springbootdemo.dao.ArticleDao
import com.futao.springbootdemo.foundation.LogicException
import com.futao.springbootdemo.model.entity.BaseEntity
import com.futao.springbootdemo.model.system.ErrorMessage
import com.futao.springbootdemo.service.DB2ElasticSearchService
import org.reflections.Reflections
import org.slf4j.LoggerFactory
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.stereotype.Service
import javax.annotation.Resource

/**
 * 同步数据库中的数据到es
 * TODO("待实现，思路：获取@Document标记的类，从数据库中load相应的表")
 *
 * @author futao
 * Created on 2018/10/25.
 */
@Service
open class DB2ElasticSearchServiceImpl : DB2ElasticSearchService {

    private val logger = LoggerFactory.getLogger(DB2ElasticSearchServiceImpl::class.java)
    @Resource
    private lateinit var articleMapper: ArticleDao
//    @Resource
//    private lateinit var elasricTemplate: ElasticsearchTemplate
//    @Resource
//    private lateinit var articleSearchDao: ArticleSearchDao

    override fun sync() {
        val reflections = Reflections("com.futao.springbootdemo.model.entity")
        val classes = reflections.getTypesAnnotatedWith(Document::class.java)
        logger.info("数据库数据同步elasticsearch开始...")
        classes.forEach { it ->
            if (BaseEntity::class.java.isAssignableFrom(it)) {
                //数据同步
//                articleSearchDao.saveAll(articleMapper.list())
            } else {
                throw LogicException.le(ErrorMessage.LogicErrorMessage.REBUILD_ELASTICSEARCH_FAIL_ENTITY_MUST_EXTENDS_BASE_ENTITY, arrayOf(it.name))
            }
        }


    }
}