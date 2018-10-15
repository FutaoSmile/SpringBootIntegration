### # 环境
* java 1.8
* SpringBoot 2.0.5.RELEASE
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

> swagger api地址: [http://localhost:8888/swagger-ui.html#!](http://localhost:8888/swagger-ui.html#!)

> druid连接池地址: [http://localhost:8888/druid/index.html](http://localhost:8888/druid/index.html)

> 打包生成jar包 `java -jar ./build/libs/springmvcdemo-0.0.1-SNAPSHOT.jar`
    
> 运行 `gradle clean build -x test`
