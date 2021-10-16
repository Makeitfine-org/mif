/*
 *  Created under not commercial project "Make it fine"
 *
 * Copyright 2017-2021
 *  @author stingion
 */

package com.stingion.makeitfine.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// Access to swagger ui: http://localhost:8080/swagger-ui.html#/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.stingion.makeitfine"))
                .build()
                .apiInfo(apiInfo())
                .tags(
                        new Tag("LoginController", "Login based with Spring security"),
                        new Tag("StartUpController", "Index page"),
                        new Tag("UserInfoController", "User info"),
                        new Tag("UserController", "REST API for CRUD operations for users"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API for \"Make it fine\" project")
                .version("1.0.0")
                .build();
    }
}
