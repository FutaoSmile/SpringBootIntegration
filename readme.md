![1](https://www.jetbrains.com/company/press/#images-logo)
感谢JetBrains提供IDEA与DataGrip软件支持 https://www.jetbrains.com/?from=SpringBootIntegration

github 🔗[https://github.com/FutaoSmile/springbootFramework](https://github.com/FutaoSmile/springbootFramework)

gitee 🔗 [https://gitee.com/FutaoSmile/springboot_framework](https://gitee.com/FutaoSmile/springboot_framework)

<p align="center">

![https://img.shields.io/badge/jdk-v1.8-green.svg](https://img.shields.io/badge/jdk-v1.8-green.svg)
![https://img.shields.io/badge/version-v0.0.1--SNAPSHOT-green.svg](https://img.shields.io/badge/version-v0.0.1--SNAPSHOT-green.svg)

## 此项目依赖于公共组件LazyerStarter
[https://github.com/FutaoSmile/LazyerStarter](https://github.com/FutaoSmile/LazyerStarter)

> 先将`lazyer`那个项目clone下来，再在`lazyer`项目下执行`mvn clean install -Dmaven.test.skip=true`将`lazyer`组件安装到本地maven仓库，再启动此项目即可(需要修改数据库账号密码)


git添加多远程仓库

` git remote set-url --add origin git@github.com:FutaoSmile/springbootFramework.git`

### # 配合配套文章食用更加哦~
*   [SpringBoot2.0统一返回Rest风格数据结构与统一异常处理](https://www.jianshu.com/p/9ff254413e9d)
*   [基于注解的用户登录权限拦截Spring HandlerInterceptor](https://www.jianshu.com/p/657fa7118e84)
*   [SpringBoot 2.0参数校验Hibernate Validator](https://www.jianshu.com/p/6f3d809c6705)
*   [自定义Hibernate Validator校验注解](https://www.jianshu.com/p/86c318c023cb)
*   [@ConfigurationProperties注解的使用与@Value的使用](https://www.jianshu.com/p/1bb95eb83ca6)
*   [SpringBoot整合druid连接池](https://www.jianshu.com/p/0631755f9983)
*   [SpringBoot整合redis从安装到FLUSHALL](https://www.jianshu.com/p/38b733f7f5f3)
*   [为什么发送邮件要使用消息队列而不是多线程](https://www.jianshu.com/p/ea6ed45fc5b8)
*   [SpringBoot 发送邮件](https://www.jianshu.com/p/65a1f7f63522)
*   [SpringBoot整合RocketMQ消息队列](https://www.jianshu.com/p/dd7ca2d10767)
*   [IntelliJ IDEA个人配置（持续更新）](https://www.jianshu.com/p/d9d7a14927ea)
*   [SpringBoot整合elasticsearch全文检索入门](https://www.jianshu.com/p/d8abebf7aa62)
*   [SpringBoot 403 跨域解决方案](https://www.jianshu.com/p/535930a6e572)
*   [基于切面与注解的用户权限拦截](https://www.jianshu.com/p/71200d94a9bf)
*   [SpringBoot国际化](https://www.jianshu.com/p/7784968e1659)
*   [CrudBoy快乐水-编写代码生成器](https://www.jianshu.com/p/d08905064f40)
*   [跨域的原因以及解决方案](https://www.jianshu.com/p/106d6e0bb43d)
*   [【牛🐂🍺】使用Redis作为Mybatis的二级缓存MybatisCacheRedis](https://www.jianshu.com/p/65d145d8e8dd)

### # 环境
* SpringBoot 2.0.5.RELEASE
* java 1.8
* redis
* rocket mq 4.3.0
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
   * mybatis + redis实现的二级缓存

> 用户登录(满足分布式)
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

> 整合redis

> 发送邮件

> 整合RocketMq消息队列

> 整合RabbitMq消息队列
* 消息的可靠性
    * queue与message的持久化
    * 发送确认-手动ack
        * 异常情况下将message丢到redis再进行其他处理
    * 接收确认-手动ack
        * 处理失败重新丢回mq，再次消费

> logback 日志系统

> 解决跨域访问

> 获取汉字拼音的首字母

> 分布式session管理 => springSession + redis

> mybatis-plus(待优化)
[https://mp.baomidou.com/](https://mp.baomidou.com/)

> 国际化

> HttpClient 

> Spring Security & apache shiro (待完善)
 
> 代码生成器

> 行级锁`select for update`的使用

> 一键生成markdown接口文档（目前依赖于swagger），不必再手动维护
* 待优化的点:
    * parameter为自定义的数据类型时候无法获取对应的数据类型
    * ~~未按照controller进行分组~~
    * $ref无法解析问题-> fastJson issue https://github.com/alibaba/fastjson/issues/2429
* 特点
    * 多数据源 - 同时将多个项目的swagger接口文档加载到一个文件中
    * 如果请求参数被@ApiParam标注，则需要手动标记required=true/false，否则会直接读取@RequestParameter()的required
### # 项目结构
```
src
|-main 源代码
    |-java java代码
        |-com.futao.springbootdemo
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

> 138.128.197.162
> 登陆地址: [http://localhost:8888/login.html](http://localhost:8888/login.html)

> swagger api地址: [http://localhost:8888/swagger-ui.html#!](http://localhost:8888/swagger-ui.html#!)

> druid连接池地址: [http://localhost:8888/druid/index.html](http://localhost:8888/druid/index.html)

> rocketmq控制台地址: [http://localhost:8088/#/](http://localhost:8088/#/)

> kibana地址: [http://localhost:5601/app/kibana](http://localhost:5601/app/kibana)

> 打包生成jar包 `gradle clean build -x test`
    
> 运行 `java -jar ./build/libs/springbootdemo-0.0.1-SNAPSHOT.jar`


### # TODO items
* 编写根据swagger-api-doc生成pdf接口文档的工具
* ~~mybatis分页~~ -> sql分页

* ~~elastic search全文检索~~
    * 查询语句
    * elasticsearch分词器
    * 从数据库中读取所有数据(建立/重建索引)
        * 自己实现
        * elastic-jdbc
* 接口限流
    * 漏斗算法
    * 令牌桶算法
    * Sentinel
* shiro 安全框架
* solr 全文检索框架
* 简单实用的Excel导入导出
* WebSecurity
* Condition接口与@Conditional注解的使用
* @Enable* 注解原理与@Import注解的使用
* ImportSelector与ImportBeanDefinitionRegistrar接口的使用
* 服务端消息推送
    * SSE 
    * Socket
* zxing二维码
* hashMap原理
* 一种用于 API 的查询语言-非常新颖，颠覆以往的认知 [http://graphql.cn/](http://graphql.cn/)
* 分布式日志收集logstash+kibana
* 心跳

报错内容:

Error running 'ServiceStarter': Command line is too long. Shorten command line for ServiceStarter or also for Application default configuration.


解法:

修改项目下 `.idea\workspace.xml`，找到标签 `<component name="PropertiesComponent"> `， 在标签里加一行  `<property name="dynamic.classpath" value="true" />`


在项目启动时候选择激活的profile
`java -jar springmvcdemo-0.0.1-SNAPSHOT.jar --spring.profiles.active=prd` --server.port=8888




    /*--------------------------------------------
    |         C O N S T R U C T O R S           |
    ============================================*/

    /*--------------------------------------------
    |  A C C E S S O R S / M O D I F I E R S    |
    ============================================*/

### # 开发者profile的解决方案:
[使用gradle构建的springboot项目在IDEA根据不同的开发人员读取不同的配置文件](https://www.jianshu.com/p/27abbeccbebe)

新建application-developerName.yml
在IDEA中设置 ProgramArguments:`spring.profiles.active=developerName`


### # Rocketmq
* 启动nameserver ` ~/soft/rocketmq-all-4.3.0/distribution/target/apache-rocketmq/bin  ./mqnamesrv`
* 启动broker `~/soft/rocketmq-all-4.3.0/distribution/target/apache-rocketmq/bin  ./mqbroker -n 127.0.0.1:9876 autoCreateTopicEnable=true`
* 启动console `java -jar ./docs/jars/rocketmq-console-ng-1.0.0.jar`
* 如果没装rocketmq，可关闭该功能(
