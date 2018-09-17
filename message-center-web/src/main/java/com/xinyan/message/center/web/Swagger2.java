package com.xinyan.message.center.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * user:gambo
 * date:2018/7/27
 * time:18:10
 * version:1.0.0
 */
@Slf4j
@Configuration
@EnableSwagger2
public class Swagger2 {

    private static final String DEFAULT_SWAGGER="false";

    @Value("${swagger.open}")
    private Boolean swagger;
    @Bean
    public Docket createRestApi() {
       log.info("swagger配置信息:{}",swagger);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xinyan.message.center.web.controller"))
                .paths(swagger?PathSelectors.any():PathSelectors.none())
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("新颜", "www.xinyan.com", "");
        return new ApiInfoBuilder()
                .title("消息中心")
                .description("消息中心相关接口")
                .termsOfServiceUrl("www.xinyan.com")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
