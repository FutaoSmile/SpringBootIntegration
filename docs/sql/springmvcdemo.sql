/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : springmvcdemo

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 08/03/2019 02:58:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for futao_article
-- ----------------------------
DROP TABLE IF EXISTS `futao_article`;
CREATE TABLE `futao_article`
(
  `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `title`            varchar(20) COLLATE utf8_unicode_ci      DEFAULT NULL,
  `description`      varchar(200) COLLATE utf8_unicode_ci     DEFAULT NULL,
  `content`          varchar(5000) COLLATE utf8_unicode_ci    DEFAULT NULL,
  `author`           varchar(32) COLLATE utf8_unicode_ci      DEFAULT NULL,
  `visit_times`      int(11)                                  DEFAULT NULL,
  `create_time`      timestamp                           NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp                           NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `futao_article_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_article
-- ----------------------------
BEGIN;
INSERT INTO `futao_article`
VALUES ('1', '标题', '描述', '内容', '作者', 99, '2019-03-05 16:44:17', '2019-03-05 16:44:17');
INSERT INTO `futao_article`
VALUES ('1a22ad171d5f420fbaa1872b9258ab37', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:28:17', '2019-03-07 17:28:17');
INSERT INTO `futao_article`
VALUES ('32549432972f46a6a4b680ba215f4388', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:38:09', '2019-03-07 17:38:09');
INSERT INTO `futao_article`
VALUES ('3f90dbe42cc041969600bfa64324a626', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:24:25', '2019-03-07 17:24:25');
INSERT INTO `futao_article`
VALUES ('71bf29225ad145acad681690e3db5eae', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:48:49', '2019-03-07 17:48:49');
INSERT INTO `futao_article`
VALUES ('77fef9b632c94c3381ee0c16b8bfe3c9', '十三届全国人大二次会议在京开幕', '两会',
        '新华社北京3月5日电　第十三届全国人民代表大会第二次会议5日上午在人民大会堂开幕。近3000名全国人大代表肩负人民重托出席盛会，认真履行宪法和法律赋予的神圣职责。  　　春暖大地，万物勃发。人民大会堂万人大礼堂气氛庄重热烈，主席台帷幕正中的国徽在鲜艳的红旗映衬下熠熠生辉。  　　大会主席团常务主席、执行主席栗战书主持大会。大会主席团常务主席、执行主席王晨、曹建明、张春贤、沈跃跃、吉炳轩、艾力更·依明巴海、万鄂湘、陈竺、王东明、白玛赤林、丁仲礼、郝明金、蔡达峰、武维华、杨振武在主席台执行主席席就座。  　　习近平、李克强、汪洋、王沪宁、赵乐际、韩正、王岐山和大会主席团成员在主席台就座。',
        '6584a071432c49d4939a1f6beb045d7f', 0, '2019-03-05 19:05:15', '2019-03-05 19:05:15');
INSERT INTO `futao_article`
VALUES ('97fbf0308ca2420a94038b74173f29e6', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:29:50', '2019-03-07 17:29:50');
INSERT INTO `futao_article`
VALUES ('9ed1cc5ad8ae4ce0824a72d128ec11b0', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:18:07', '2019-03-07 17:18:07');
INSERT INTO `futao_article`
VALUES ('bd49c996d0a341f8b9d1f33a56d5e1c3', '十三届全国人大二次会议在京开幕', '两会',
        '新华社北京3月5日电　第十三届全国人民代表大会第二次会议5日上午在人民大会堂开幕。近3000名全国人大代表肩负人民重托出席盛会，认真履行宪法和法律赋予的神圣职责。  　　春暖大地，万物勃发。人民大会堂万人大礼堂气氛庄重热烈，主席台帷幕正中的国徽在鲜艳的红旗映衬下熠熠生辉。  　　大会主席团常务主席、执行主席栗战书主持大会。大会主席团常务主席、执行主席王晨、曹建明、张春贤、沈跃跃、吉炳轩、艾力更·依明巴海、万鄂湘、陈竺、王东明、白玛赤林、丁仲礼、郝明金、蔡达峰、武维华、杨振武在主席台执行主席席就座。  　　习近平、李克强、汪洋、王沪宁、赵乐际、韩正、王岐山和大会主席团成员在主席台就座。',
        '6584a071432c49d4939a1f6beb045d7f', 0, '2019-03-05 19:06:21', '2019-03-05 19:06:21');
INSERT INTO `futao_article`
VALUES ('bfd87374fdf24fea935c813599c6d1f1', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:26:00', '2019-03-07 17:26:00');
INSERT INTO `futao_article`
VALUES ('d10064f2f9ac4a3793336510a93cbc5e', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:20:06', '2019-03-07 17:20:06');
INSERT INTO `futao_article`
VALUES ('ed6cb3abab054cd78b6682fb0d10dc01', '标致', '喵喵', '康康硁硁', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-07 17:25:21', '2019-03-07 17:25:21');
COMMIT;

-- ----------------------------
-- Table structure for futao_article_tag
-- ----------------------------
DROP TABLE IF EXISTS `futao_article_tag`;
CREATE TABLE `futao_article_tag`
(
  `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `article_id`       varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `tag_id`           varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `create_time`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `futao_article_tag_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_article_tag
-- ----------------------------
BEGIN;
INSERT INTO `futao_article_tag`
VALUES ('77fef9b632c94c3381ee0c16b8bfe311', '77fef9b632c94c3381ee0c16b8bfe3c9', '2', '2019-03-06 15:03:55',
        '2019-03-06 15:03:55');
INSERT INTO `futao_article_tag`
VALUES ('77fef9b632c94c3381ee0c16b8bfe3c9', '77fef9b632c94c3381ee0c16b8bfe3c9', '1', '2019-03-06 15:03:37',
        '2019-03-06 15:03:37');
COMMIT;

-- ----------------------------
-- Table structure for futao_order
-- ----------------------------
DROP TABLE IF EXISTS `futao_order`;
CREATE TABLE `futao_order`
(
  `id`             varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '订单主键',
  `userId`         varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '用户id',
  `erpOrderId`     varchar(32) COLLATE utf8_unicode_ci NOT NULL COMMENT '进销存订单id',
  `remark`         varchar(300) COLLATE utf8_unicode_ci         DEFAULT NULL COMMENT '备注',
  `createTime`     timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `lastModifyTime` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  UNIQUE KEY `futao_order_erpOrderId_uindex` (`erpOrderId`),
  UNIQUE KEY `futao_order_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='订单表';

-- ----------------------------
-- Records of futao_order
-- ----------------------------
BEGIN;
INSERT INTO `futao_order`
VALUES ('d6545bd7be3c4f9a876b59ced8e4cf77', '5e8aaf7e2e6945d68ef573a09badc17e', 'addOrUpdateByReplace',
        '已经存在应该修改，并且会删除之前的数据，请注意createTime和lastModifyTime', '2018-11-22 22:23:28', '2018-11-22 22:23:28');
INSERT INTO `futao_order`
VALUES ('73111b9d58034219ac6685d45cb2014a', '5e8aaf7e2e6945d68ef573a09badc17e', 'addOrUpdateByReplace--2222', '大吉大利',
        '2018-11-22 22:24:45', '2018-11-22 22:24:45');
COMMIT;

-- ----------------------------
-- Table structure for futao_permission
-- ----------------------------
DROP TABLE IF EXISTS `futao_permission`;
CREATE TABLE `futao_permission`
(
  `id`                    varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `permissionName`        varchar(100) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `permissionDescription` varchar(200) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `createTime`            timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime`        timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='权限表';

-- ----------------------------
-- Table structure for futao_role
-- ----------------------------
DROP TABLE IF EXISTS `futao_role`;
CREATE TABLE `futao_role`
(
  `id`              varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `roleName`        varchar(100) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `createTime`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime`  timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `roleDescription` varchar(200) COLLATE utf8_unicode_ci         DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='角色表';

-- ----------------------------
-- Table structure for futao_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `futao_role_permission`;
CREATE TABLE `futao_role_permission`
(
  `id`             varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `roleId`         varchar(32) COLLATE utf8_unicode_ci          DEFAULT NULL,
  `permissionId`   varchar(32) COLLATE utf8_unicode_ci          DEFAULT NULL,
  `createTime`     timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='角色权限表';

-- ----------------------------
-- Table structure for futao_tag
-- ----------------------------
DROP TABLE IF EXISTS `futao_tag`;
CREATE TABLE `futao_tag`
(
  `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `tag_name`         varchar(20) COLLATE utf8_unicode_ci          DEFAULT NULL,
  `create_time`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `futao_tag_id_uindex` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_tag
-- ----------------------------
BEGIN;
INSERT INTO `futao_tag`
VALUES ('1', '热点', '2019-03-06 15:04:26', '2019-03-06 15:04:26');
INSERT INTO `futao_tag`
VALUES ('2', '两会', '2019-03-06 15:04:26', '2019-03-06 15:04:26');
COMMIT;

-- ----------------------------
-- Table structure for futao_user
-- ----------------------------
DROP TABLE IF EXISTS `futao_user`;
CREATE TABLE `futao_user`
(
  `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `username`         varchar(100) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `password`         varchar(300) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `age`              varchar(3) COLLATE utf8_unicode_ci           DEFAULT NULL,
  `mobile`           varchar(11) COLLATE utf8_unicode_ci          DEFAULT NULL,
  `email`            varchar(100) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `address`          varchar(300) COLLATE utf8_unicode_ci         DEFAULT NULL,
  `create_time`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `last_modify_time` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `status`           int(11)                             NOT NULL DEFAULT '1',
  `sex`              int(11)                                      DEFAULT '0',
  `role`             int(11)                                      DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `futao_user_id_uindex` (`id`),
  UNIQUE KEY `futao_user_email_uindex` (`email`),
  KEY `mobile_password` (`mobile`, `password`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='用户表';

-- ----------------------------
-- Records of futao_user
-- ----------------------------
BEGIN;
INSERT INTO `futao_user`
VALUES ('3a175e03721a48be81cfc8508c1e101f', NULL, NULL, NULL, NULL, '7201156@qq.com', NULL, '2018-12-11 13:19:08',
        '2018-12-11 13:19:08', 0, NULL, 1);
INSERT INTO `futao_user`
VALUES ('6584a071432c49d4939a1f6beb045d7f', 'admin', '82320d5140d5efb1762e27edb606922b', '18', '18797811992',
        '1185172056@qq.com', '浙江省杭州市', '2018-12-11 11:43:29', '2018-12-11 11:43:29', 1, 1, 2);
INSERT INTO `futao_user`
VALUES ('764bc558934847de8ee7318f72e09d2a', NULL, NULL, NULL, NULL, 'taof@wicrenet.com', NULL, '2018-12-11 14:27:58',
        '2018-12-11 14:27:58', 0, NULL, 1);
INSERT INTO `futao_user`
VALUES ('b68d521c69664f82a69bf965f23d2a9a', NULL, NULL, NULL, NULL, '72056@qq.com', NULL, '2018-12-11 13:18:26',
        '2018-12-11 13:18:26', 0, NULL, 1);
INSERT INTO `futao_user`
VALUES ('edfe831da0a04709bb76f88f85f23790', NULL, '82320d5140d5efb1762e27edb606922b', NULL, '123', '55555@qq.com', NULL,
        '2018-12-13 11:18:34', '2018-12-13 11:18:34', 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for futao_user_role
-- ----------------------------
DROP TABLE IF EXISTS `futao_user_role`;
CREATE TABLE `futao_user_role`
(
  `id`             varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `userId`         varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `roleId`         varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `createTime`     timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lastModifyTime` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci COMMENT ='用户角色表';

SET FOREIGN_KEY_CHECKS = 1;
