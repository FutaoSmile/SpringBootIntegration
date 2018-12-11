create table futao_order
(
  id             varchar(32)                         not null comment '订单主键',
  userId         varchar(32)                         not null comment '用户id',
  erpOrderId     varchar(32)                         not null comment '进销存订单id',
  remark         varchar(300)                        null comment '备注',
  createTime     timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null comment '最后修改时间',
  constraint futao_order_erpOrderId_uindex
    unique (erpOrderId),
  constraint futao_order_id_uindex
    unique (id)
)
  comment '订单表';

alter table futao_order
  add primary key (id);

create table futao_user
(
  id             varchar(32)                         not null,
  username       varchar(100)                        null,
  password       varchar(300)                        null,
  age            varchar(3)                          null,
  mobile         varchar(11)                         null,
  email          varchar(100)                        null,
  address        varchar(300)                        null,
  createTime     timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null,
  constraint futao_user_id_uindex
    unique (id)
)
  comment '用户表';

alter table futao_user
  add primary key (id);

