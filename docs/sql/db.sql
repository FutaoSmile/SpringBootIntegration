create table futao_article
(
  id               varchar(32)                         not null,
  title            varchar(20)                         null,
  description      varchar(200)                        null,
  content          varchar(5000)                       null,
  author           varchar(32)                         null,
  visit_times      int                                 null,
  create_time      timestamp default CURRENT_TIMESTAMP null,
  last_modify_time timestamp default CURRENT_TIMESTAMP null,
  constraint futao_article_id_uindex
    unique (id)
);

alter table futao_article
  add primary key (id);

create table futao_order
(
  id             varchar(32)                         not null
    comment '订单主键',
  userId         varchar(32)                         not null
    comment '用户id',
  erpOrderId     varchar(32)                         not null
    comment '进销存订单id',
  remark         varchar(300)                        null
    comment '备注',
  createTime     timestamp default CURRENT_TIMESTAMP not null
    comment '创建时间',
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null
    comment '最后修改时间',
  constraint futao_order_erpOrderId_uindex
    unique (erpOrderId),
  constraint futao_order_id_uindex
    unique (id)
)
  comment '订单表';

create table futao_permission
(
  id                    varchar(32)                         not null
    primary key,
  permissionName        varchar(100)                        null,
  permissionDescription varchar(200)                        null,
  createTime            timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime        timestamp default CURRENT_TIMESTAMP not null
)
  comment '权限表';

create table futao_role
(
  id              varchar(32)                         not null
    primary key,
  roleName        varchar(100)                        null,
  createTime      timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime  timestamp default CURRENT_TIMESTAMP not null,
  roleDescription varchar(200)                        null
)
  comment '角色表';

create table futao_role_permission
(
  id             varchar(32)                         not null
    primary key,
  roleId         varchar(32)                         null,
  permissionId   varchar(32)                         null,
  createTime     timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null
)
  comment '角色权限表';

create table futao_user
(
  id               varchar(32)                         not null,
  username         varchar(100)                        null,
  password         varchar(300)                        null,
  age              varchar(3)                          null,
  mobile           varchar(11)                         null,
  email            varchar(100)                        null,
  address          varchar(300)                        null,
  create_time      timestamp default CURRENT_TIMESTAMP not null,
  last_modify_time timestamp default CURRENT_TIMESTAMP not null,
  status           int       default '1'               not null,
  sex              int       default '0'               null,
  role             int       default '1'               null,
  constraint futao_user_email_uindex
    unique (email),
  constraint futao_user_id_uindex
    unique (id)
)
  comment '用户表';

create index mobile_password
  on futao_user (mobile, password);

alter table futao_user
  add primary key (id);

create table futao_user_role
(
  id             varchar(32)                         not null
    primary key,
  userId         varchar(32)                         not null,
  roleId         varchar(32)                         not null,
  createTime     timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null
)
  comment '用户角色表';


INSERT INTO springmvcdemo.futao_user (id, username, password, age, mobile, email, address, create_time,
                                      last_modify_time, status, sex, role)
VALUES ('3a175e03721a48be81cfc8508c1e101f', null, null, null, null, '7201156@qq.com', null, '2018-12-11 13:19:08',
        '2018-12-11 13:19:08', 0, null, 1);
INSERT INTO springmvcdemo.futao_user (id, username, password, age, mobile, email, address, create_time,
                                      last_modify_time, status, sex, role)
VALUES ('6584a071432c49d4939a1f6beb045d7f', 'admin', '82320d5140d5efb1762e27edb606922b', '18', '18797811992',
        '1185172056@qq.com', '浙江省杭州市', '2018-12-11 11:43:29', '2018-12-11 11:43:29', 1, 1, 2);
INSERT INTO springmvcdemo.futao_user (id, username, password, age, mobile, email, address, create_time,
                                      last_modify_time, status, sex, role)
VALUES ('764bc558934847de8ee7318f72e09d2a', null, null, null, null, 'taof@wicrenet.com', null, '2018-12-11 14:27:58',
        '2018-12-11 14:27:58', 0, null, 1);
INSERT INTO springmvcdemo.futao_user (id, username, password, age, mobile, email, address, create_time,
                                      last_modify_time, status, sex, role)
VALUES ('b68d521c69664f82a69bf965f23d2a9a', null, null, null, null, '72056@qq.com', null, '2018-12-11 13:18:26',
        '2018-12-11 13:18:26', 0, null, 1);
INSERT INTO springmvcdemo.futao_user (id, username, password, age, mobile, email, address, create_time,
                                      last_modify_time, status, sex, role)
VALUES ('edfe831da0a04709bb76f88f85f23790', null, '82320d5140d5efb1762e27edb606922b', null, '123', '55555@qq.com', null,
        '2018-12-13 11:18:34', '2018-12-13 11:18:34', 0, 0, 1);