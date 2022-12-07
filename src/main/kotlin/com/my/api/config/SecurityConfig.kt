package com.my.api.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "auth")
class SecurityConfig(
    var whiteList: List<String> = listOf()
) : WebSecurityConfigurerAdapter(){

    override fun configure(web: WebSecurity) {
        web.ignoring()
            .antMatchers(*whiteList.toTypedArray())
    }

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()

        http.cors()

        http.formLogin()
            .disable()

        http.exceptionHandling()
            .accessDeniedHandler{
                _, res, _ -> res.sendRedirect("/denied")
            }
            .authenticationEntryPoint(){
                _, res, _ -> res.sendRedirect("/denied")
            }

        http.authorizeRequests()
            .anyRequest()
            .authenticated()
    }
}