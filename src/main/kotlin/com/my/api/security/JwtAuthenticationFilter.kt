package com.my.api.security

import com.my.api.common.exception.JwtNotPresentException
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtAuthenticationFilter : OncePerRequestFilter() {

    private val authorizationHeaderName = "Authorization"

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try{
            val authorizationHeader = request.getHeader(authorizationHeaderName)

            authorizationHeader?: throw JwtNotPresentException()

        } catch (ex: JwtNotPresentException) {

        }

        filterChain.doFilter(request,response)

    }
}