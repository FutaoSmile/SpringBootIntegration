### # 环境
* SpringBoot 2.0.5.RELEASE
* java 1.8
* rocketmq 4.3.0
* elasticsearch 5.6.11
* gradle
* mybatis
* mysql
* idea
* git

### # 实现的功能：
 
> 统一返回的数据结构

> 统一异常处理(系统异常/业务逻辑异常)

> 服务端请求参数校验

> Swagger接口文档

> 整合mybatis

> 用户登录
    基于`httpsession`

> 控制接口的访问权限（必须登录才能访问/可不登录直接访问的资源）
 * 基于 `Annotation`+`SpringMvc Interceptor`拦截器

> 请求日志记录
 * 基于`SpringMvc Interceptor`拦截器

> 在线用户统计
 * 基于`HttpSessionListener`监听器

> 接口请求次数统计
 * 基于`SpringMvc Interceptor`拦截器

> 敏感词检测
 * 基于`Annotation`+`Hibernate Validator`+`SpringMvc Interceptor`
 
> 第三方接口统一验签
 * 基于`Annotation`+`SpringMvc Interceptor`
 
> 简单易用的sql分页

> 整合redis

> 发送邮件

> 整合rocketMq消息队列
 
### # 项目结构
```
src
|-main 源代码
    |-java java代码
        |-com.futao.springmvcdemo
            |-annotaion 项目用到的自定义注解
            |-controller 接口
            |-dao 持久层
            |-foundation 支持项目运行的一些功能代码
            |-model 实体层
            |-service 业务逻辑层
            |-utils 工具类
            |-SpringmvcdemoApplication.java 启动类
    |-kotlin kotlin代码
    |-resource 配置文件
|-test
```
> 登陆地址: [http://localhost:8888/login.html](http://localhost:8888/login.html)

> swagger api地址: [http://localhost:8888/swagger-ui.html#!](http://localhost:8888/swagger-ui.html#!)

> druid连接池地址: [http://localhost:8888/druid/index.html](http://localhost:8888/druid/index.html)

> rocketmq控制台地址: [http://localhost:8088/#/](http://localhost:8088/#/)

> kibana地址: [http://localhost:5601/app/kibana](http://localhost:5601/app/kibana)

> 打包生成jar包 `java -jar ./build/libs/springmvcdemo-0.0.1-SNAPSHOT.jar`
    
> 运行 `gradle clean build -x test`


### # TODO items
* ~~mybatis分页~~ -> sql分页
* ~~缓存~~
    * redis缓存过期时间
* ~~elastic search全文检索~~
    * 查询语句
    * elasticsearch分词器
    * 从数据库中读取所有数据(建立/重建索引)
* 接口限流
    * 漏斗算法
    * 令牌桶算法
* shiro 安全框架
* solr 全文检索框架


```xml
报错内容:

Error running 'ServiceStarter': Command line is too long. Shorten command line for ServiceStarter or also for Application default configuration.



解法:

修改项目下 .idea\workspace.xml，找到标签 <component name="PropertiesComponent"> ， 在标签里加一行  <property name="dynamic.classpath" value="true" />
--------------------- 
作者：ZXJ_1223 
来源：CSDN 
原文：https://blog.csdn.net/ZXJ_1223/article/details/80611089 
版权声明：本文为博主原创文章，转载请附上博文链接！

```