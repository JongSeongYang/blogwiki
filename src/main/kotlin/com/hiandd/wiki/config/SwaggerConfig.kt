package com.hiandd.wiki.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.*
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.contexts.SecurityContext
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import java.util.*

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun productApi(): Docket {
        return Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .securityContexts(listOf(securityContext()))
                .securitySchemes(listOf(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hiandd.wiki"))
                .paths(PathSelectors.any())
                .build()

    }

    fun apiInfo() : ApiInfo {
        return ApiInfoBuilder()
                .title("BLOG WIKI API")
                .description("Blog Wiki Description of API")
                .version("0.0.1")
                .termsOfServiceUrl("http://hiandd.com")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .extensions(Collections.emptyList())
                .contact(
                        Contact(
                                "JongSeong",
                                "http://baegoon.kr",
                                "jsyang@hiandd.com"
                        )
                )
                .build();
    }


    fun apiKey(): ApiKey {
        return ApiKey("JWT", "Authorization", "header")
    }

    fun securityContext():SecurityContext {
        return SecurityContext.builder().securityReferences(defaultAuth()).build()
    }

    fun defaultAuth(): List<SecurityReference?>? {
        val authorizationScope = AuthorizationScope("global", "accessEverything")
        val authorizationScopes = arrayOfNulls<AuthorizationScope>(1)
        authorizationScopes[0] = authorizationScope
        return listOf(SecurityReference("JWT", authorizationScopes))
    }
}