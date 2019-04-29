/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : springmvcdemo

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 29/04/2019 15:24:21
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
VALUES ('0bfa93e3bd6f439cbc88e42e7e0eef81', 'mybatisRedis测试', '测试',
        'mybatisRedis测试mybatisRedis测试mybatisRedis测试mybatisRedis测试', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-12 13:18:45', '2019-03-12 13:18:45');
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
VALUES ('72f64d4ce29a4b54b5d4092e9cae3db0', 'mybatisRedis测试', '测试',
        'mybatisRedis测试mybatisRedis测试mybatisRedis测试mybatisRedis测试', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-12 13:23:15', '2019-03-12 13:23:15');
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
VALUES ('d668cf695a3f41398950d80ceda786bb', 'mybatisRedis测试', '测试',
        'mybatisRedis测试mybatisRedis测试mybatisRedis测试mybatisRedis测试', '6584a071432c49d4939a1f6beb045d7f', 0,
        '2019-03-12 13:29:17', '2019-03-12 13:29:17');
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
-- Table structure for futao_register_email_send_log
-- ----------------------------
DROP TABLE IF EXISTS `futao_register_email_send_log`;
CREATE TABLE `futao_register_email_send_log`
(
    `id`               varchar(32) COLLATE utf8_unicode_ci  NOT NULL COMMENT '主键',
    `create_time`      timestamp                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modify_time` timestamp                            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
    `email`            varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT '注册邮箱',
    `send_times`       int(11)                              NOT NULL DEFAULT '1' COMMENT '发送验证码次数',
    `usered`           tinyint(1)                           NOT NULL DEFAULT '0' COMMENT '是否已经注册成为了正式用户，1=true，2=false',
    PRIMARY KEY (`id`),
    UNIQUE KEY `futao_register_email_send_log_email_uindex` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_register_email_send_log
-- ----------------------------
BEGIN;
INSERT INTO `futao_register_email_send_log`
VALUES ('11', '2019-03-11 16:51:14', '2019-03-11 16:51:14', '11@qq.com', 1, 0);
INSERT INTO `futao_register_email_send_log`
VALUES ('55474a6da60842f7a2ad4f482eaf9d4e', '2019-04-08 16:39:03', '2019-04-08 16:39:03', '55555@qq.com', 1, 0);
INSERT INTO `futao_register_email_send_log`
VALUES ('cb68daf0a67944dfabfe117ec469d1dc', '2019-03-11 17:38:14', '2019-03-11 17:38:14', '1185172056@qq.com', 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for futao_review
-- ----------------------------
DROP TABLE IF EXISTS `futao_review`;
CREATE TABLE `futao_review`
(
    `id`               varchar(32) NOT NULL COMMENT 'id',
    `article`          varchar(32) NOT NULL COMMENT '评论的文章id',
    `parent_review`    varchar(32)          DEFAULT NULL COMMENT '父级评论',
    `content`          varchar(50) NOT NULL DEFAULT '' COMMENT '评论的内容',
    `user`             varchar(32) NOT NULL COMMENT '发表评论的用户',
    `create_time`      datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `last_modify_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='review';

-- ----------------------------
-- Records of futao_review
-- ----------------------------
BEGIN;
INSERT INTO `futao_review`
VALUES ('04e3eed190f443229add3927ea9b55b9', '1', NULL, 'diyi评论', '6584a071432c49d4939a1f6beb045d7f',
        '2019-03-18 17:16:54', '2019-03-18 17:16:54');
INSERT INTO `futao_review`
VALUES ('695f380e90d94f3e8bc2ff6f445fc0e9', '1', '04e3eed190f443229add3927ea9b55b9', 'diyi评论',
        '6584a071432c49d4939a1f6beb045d7f', '2019-03-18 17:13:07', '2019-03-18 17:13:07');
INSERT INTO `futao_review`
VALUES ('8ed1d4d93f4f4c05b6ed353156dceb4c', '1', '04e3eed190f443229add3927ea9b55b9', 'HQLM',
        '6584a071432c49d4939a1f6beb045d7f', '2019-03-18 17:20:08', '2019-03-18 17:20:08');
INSERT INTO `futao_review`
VALUES ('b64fea8b5d6e433d8a0cdb1c0d1f61e4', '1', 'ccc9bb2724984ff7b6fcdfce4cc82b1e', '123123',
        '6584a071432c49d4939a1f6beb045d7f', '2019-03-18 17:40:13', '2019-03-18 17:40:13');
COMMIT;

-- ----------------------------
-- Table structure for futao_role
-- ----------------------------
DROP TABLE IF EXISTS `futao_role`;
CREATE TABLE `futao_role`
(
    `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
    `role_name`        varchar(100) COLLATE utf8_unicode_ci         DEFAULT NULL,
    `create_time`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modify_time` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `role_description` varchar(200) COLLATE utf8_unicode_ci         DEFAULT NULL,
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
-- Table structure for futao_test_01
-- ----------------------------
DROP TABLE IF EXISTS `futao_test_01`;
CREATE TABLE `futao_test_01`
(
    `id`   int(11) NOT NULL AUTO_INCREMENT,
    `name` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
    `age`  int(11)                              DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_test_01
-- ----------------------------
BEGIN;
INSERT INTO `futao_test_01`
VALUES (4, '4', NULL);
INSERT INTO `futao_test_01`
VALUES (6, '5', NULL);
COMMIT;

-- ----------------------------
-- Table structure for futao_test_a
-- ----------------------------
DROP TABLE IF EXISTS `futao_test_a`;
CREATE TABLE `futao_test_a`
(
    `id`               int(11)   NOT NULL AUTO_INCREMENT,
    `balance`          int(11)            DEFAULT NULL,
    `create_time`      timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modify_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 322
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_test_a
-- ----------------------------
BEGIN;
INSERT INTO `futao_test_a`
VALUES (1, 149, '2019-04-10 17:18:11', '2019-04-10 17:18:11');
INSERT INTO `futao_test_a`
VALUES (3, 12, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (5, 12, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (6, 4, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (7, 4, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (8, 5, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (9, 46, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (10, 5, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (11, 76, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (12, 8, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (13, 7, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (14, 8, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (15, 111, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (16, 9, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (17, 9, '2019-04-16 20:16:40', '2019-04-16 20:16:40');
INSERT INTO `futao_test_a`
VALUES (19, 12, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (21, 12, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (22, 4, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (23, 4, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (24, 5, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (25, 46, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (26, 5, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (27, 76, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (28, 8, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (29, 7, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (30, 8, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (31, 111, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (32, 9, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (33, 9, '2019-04-16 20:18:22', '2019-04-16 20:18:22');
INSERT INTO `futao_test_a`
VALUES (35, 12, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (37, 12, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (38, 4, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (39, 4, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (40, 5, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (41, 46, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (42, 5, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (43, 76, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (44, 8, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (45, 7, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (46, 8, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (47, 111, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (48, 9, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (49, 9, '2019-04-16 20:19:51', '2019-04-16 20:19:51');
INSERT INTO `futao_test_a`
VALUES (51, 12, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (53, 12, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (54, 4, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (55, 4, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (56, 5, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (57, 46, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (58, 5, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (59, 76, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (60, 8, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (61, 7, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (62, 8, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (63, 111, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (64, 9, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (65, 9, '2019-04-16 20:21:16', '2019-04-16 20:21:16');
INSERT INTO `futao_test_a`
VALUES (66, 1, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (67, 12, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (69, 12, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (70, 4, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (71, 4, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (72, 5, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (73, 46, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (74, 5, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (75, 76, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (76, 8, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (77, 7, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (78, 8, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (79, 111, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (80, 9, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (81, 9, '2019-04-16 20:22:41', '2019-04-16 20:22:41');
INSERT INTO `futao_test_a`
VALUES (226, 1, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (227, 12, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (229, 12, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (230, 4, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (231, 4, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (232, 5, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (233, 46, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (234, 5, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (235, 76, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (236, 8, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (237, 7, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (238, 8, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (239, 111, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (240, 9, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (241, 9, '2019-04-17 19:24:54', '2019-04-17 19:24:54');
INSERT INTO `futao_test_a`
VALUES (306, 1, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (307, 12, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (309, 12, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (310, 4, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (311, 4, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (312, 5, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (313, 46, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (314, 5, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (315, 76, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (316, 8, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (317, 7, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (318, 8, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (319, 111, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (320, 9, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
INSERT INTO `futao_test_a`
VALUES (321, 9, '2019-04-17 19:27:44', '2019-04-17 19:27:44');
COMMIT;

-- ----------------------------
-- Table structure for futao_test_b
-- ----------------------------
DROP TABLE IF EXISTS `futao_test_b`;
CREATE TABLE `futao_test_b`
(
    `id`               varchar(32) COLLATE utf8_unicode_ci NOT NULL,
    `balance`          int(11)                                      DEFAULT NULL,
    `create_time`      timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modify_time` timestamp                           NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_unicode_ci;

-- ----------------------------
-- Records of futao_test_b
-- ----------------------------
BEGIN;
INSERT INTO `futao_test_b`
VALUES ('1', 70, '2019-04-10 17:18:17', '2019-04-10 17:18:17');
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
        '1185172056@qq.com', '浙江省杭州市', '2018-12-11 11:43:29', '2018-12-11 11:43:29', 1, 1, 1);
INSERT INTO `futao_user`
VALUES ('764bc558934847de8ee7318f72e09d2a', NULL, NULL, NULL, NULL, 'taof@wicrenet.com', NULL, '2018-12-11 14:27:58',
        '2018-12-11 14:27:58', 0, NULL, 1);
INSERT INTO `futao_user`
VALUES ('b68d521c69664f82a69bf965f23d2a9a', '', '550e1bafe077ff0b0b67f4e32f29d751', NULL, '18797811993', '72056@qq.com',
        NULL, '2018-12-11 13:18:26', '2018-12-11 13:18:26', 0, NULL, 1);
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
