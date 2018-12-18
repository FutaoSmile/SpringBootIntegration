create table if not exists futao_user
(
  id varchar(32) not null,
  username varchar(100) null,
  password varchar(300) null,
  age varchar(3) null,
  mobile varchar(11) null,
  email varchar(100) null,
  address varchar(300) null,
  createTime timestamp default CURRENT_TIMESTAMP not null,
  lastModifyTime timestamp default CURRENT_TIMESTAMP not null,
  status int default '1' not null,
  sex int default '0' null,
  role int default '1' null,
  constraint futao_user_email_uindex
  unique (email),
  constraint futao_user_id_uindex
  unique (id)
  )
  comment '用户表'
;

alter table futao_user
  add primary key (id)
;

