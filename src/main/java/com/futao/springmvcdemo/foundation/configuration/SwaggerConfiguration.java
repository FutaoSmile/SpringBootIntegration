package com.futao.springmvcdemo.foundation.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger api配置类
 *
 * @author futao
 * Created on 2018/9/19-17:25.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SpringBoot项目中使用Swagger2来构建RESTfull APIs")
                .description("描述：用来介绍服务端所有的API接口信息")
                .termsOfServiceUrl("http://localhost:8080")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.futao.springmvcdemo"))
                .paths(PathSelectors.any())
                .build();
    }
}
