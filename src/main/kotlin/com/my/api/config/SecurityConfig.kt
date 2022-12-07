package com.my.api.config

import com.my.api.security.JwtTokenAuthenticationFilter
import com.my.api.security.JwtUserDetailsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@ConfigurationProperties(prefix = "auth")
class SecurityConfig(
    var whiteList: List<String> = listOf(),
) : WebSecurityConfigurerAdapter(){

    @Autowired private lateinit var userDetailsService: JwtUserDetailsService

    override fun configure(http: HttpSecurity) {
        http.csrf()
            .disable()

        http.cors()

        http.formLogin()
            .disable()

        http.authorizeRequests()
            .antMatchers(*whiteList.toTypedArray())
            .permitAll()
            .anyRequest()
            .authenticated()

        http.exceptionHandling()
            .authenticationEntryPoint(){
                _, response, _ ->
                response.sendRedirect("/denied")
            }
            .accessDeniedHandler{
                _, response, _ ->
                response.sendRedirect("/denied")
            }

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    fun jwtAuthenticationFilter(): JwtTokenAuthenticationFilter
        = JwtTokenAuthenticationFilter(whiteList, userDetailsService)
}