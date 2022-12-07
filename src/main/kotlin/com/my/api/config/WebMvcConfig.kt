package com.my.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
@EnableWebMvc
@ConfigurationProperties(prefix = "cors")
class WebMvcConfig(
    var allowedOrigin: List<String> = listOf(),
) : WebMvcConfigurer{
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedHeaders("*")
            .allowedOrigins(*allowedOrigin.toTypedArray())
            .maxAge(3000)
    }

    @Bean
    fun swagger() : Docket
    = Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.my.api.rest"))
        .build()
        .apiInfo(apiInfo())

    fun apiInfo() : ApiInfo
    = ApiInfoBuilder()
        .version("demo")
        .description("My World")
        .title("My World")
        .build()

}