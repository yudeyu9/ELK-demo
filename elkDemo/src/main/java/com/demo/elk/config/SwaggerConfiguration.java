package com.demo.elk.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/** 
 * Swagger-UI配置 SwaggerConfiguration 
 *
 * @author Hotpotmaterial-Code2
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
  
  // 初始化信息	
  ApiInfo apiInfo() {
    return new ApiInfoBuilder().title("ELKDemo API")
        .description(
            "测试项目")
        .license("").licenseUrl("http://unlicense.org").termsOfServiceUrl("").version("1.0.0")
        .contact(new Contact("", "", "")).build();
  }

  // 配置需要在swagger中展现的接口
  @Bean
  public Docket customImplementation() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(Predicates.or(RequestHandlerSelectors.basePackage("com.demo.elk.controller"))).build()
        .apiInfo(apiInfo());
  }

}