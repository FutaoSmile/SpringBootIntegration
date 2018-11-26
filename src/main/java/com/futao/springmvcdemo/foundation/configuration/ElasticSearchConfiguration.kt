//package com.futao.springmvcdemo.foundation.configuration
//
//import org.elasticsearch.client.transport.TransportClient
//import org.elasticsearch.common.settings.Settings
//import org.elasticsearch.common.transport.InetSocketTransportAddress
//import org.elasticsearch.transport.client.PreBuiltTransportClient
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
//import java.net.InetAddress
//
///**
// * @author futao
// * Created on 2018/10/22.
// * ElasticSearch全文检索配置类
// * 可替代配置文件中的配置
// */
//@Configuration
//@EnableElasticsearchRepositories(basePackages = ["com.futao.springmvcdemo.dao"])
//open class ElasticSearchConfiguration {
//
//    @Bean
//    open fun client(): TransportClient {
//        val node = InetSocketTransportAddress(
//                InetAddress.getByName("127.0.0.1"), 9300)
//        val settings = Settings.builder()
//                .put("cluster.name", "springboot-elasticsearch")
//                .build()
//        return PreBuiltTransportClient(settings).addTransportAddress(node)
//    }
//}