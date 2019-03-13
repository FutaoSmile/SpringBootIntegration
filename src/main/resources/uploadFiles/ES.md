### # 核心概念

* Near RealTime (NRT近实时)    存->取
* Cluster集群		集群名称默认elasticsearch
* Node节点
* Document    一条数据比如一个商品信息 -> row
* Index   代表一类Document的集合，可以存放多个Type -> DB
* Type   Table
* Shard 服务高可用，（备份）

### # 启动

* 配置文件: `elasticsearch.yml`
* 启动: `./elasticsearch`
* 查看 `localhost:9200`
* Kibana: ./kibana
* 