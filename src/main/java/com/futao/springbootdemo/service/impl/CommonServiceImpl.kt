//package com.futao.springbootdemo.service.impl
//
//import com.futao.springbootdemo.model.entity.BaseEntity
//import com.futao.springbootdemo.service.CommonService
//import com.futao.springbootdemo.utils.tablePrefix
//
///**
// * @author futao
// * Created on 2018/10/20.
// */
//open class CommonServiceImpl : CommonService {
//    open fun <T : BaseEntity> T.generatorCreateTableSql() {
//        val append = StringBuilder().append("CREATE TABLE `$tablePrefix${this::class.java.simpleName.toLowerCase()}` (")
//        this::class.java.fields.forEach { field ->
//            val javaType = field.ES_TYPE
//            var sqlType = ""
//            if (javaType == String.javaClass) {
//
//                sqlType = "varchar(${field.getAnnotation()})"
//            }
//            append.append("`${field.name}` ")
//        }
//    }
//}
//
//
////CREATE TABLE `futao_user` (
////`id` varchar(32) NOT NULL,
////`username` varchar(100) DEFAULT NULL,
////`age` varchar(3) DEFAULT NULL,
////`mobile` varchar(11) DEFAULT NULL,
////`email` varchar(50) DEFAULT NULL,
////`address` varchar(200) DEFAULT NULL,
////`createtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
////`lastmodifytime` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
////`password` varchar(50) DEFAULT NULL,
////PRIMARY KEY (`id`),
////UNIQUE KEY `mobile` (`mobile`)
////) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4
