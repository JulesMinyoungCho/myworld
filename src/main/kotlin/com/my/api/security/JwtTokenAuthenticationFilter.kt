package com.my.api.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.my.api.common.exception.CommonException
import com.my.api.common.util.JwtUtil
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenAuthenticationFilter(
    private val whiteList: List<String>,
    private val userDetailsService: JwtUserDetailsService,
) : OncePerRequestFilter(){
    private val authorizationHeaderName = "Authorization"
    private val bearerPrefix = "Bearer "
    private val pathMatcher = AntPathMatcher()

    private fun checkWhiteList(uri: String): Boolean {
        whiteList.forEach { if(pathMatcher.match(it, uri)) return true }
        return false
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        var authorized = true
        var errorMsg = ""
        if(!checkWhiteList(request.requestURI)) {
            val token = request.getHeader(authorizationHeaderName)
            var claims:Map<String, Any>
            if(token == null) {
                // Token is null
                errorMsg = "토큰이 없습니다."
                authorized = false
            }else if(!token.startsWith(bearerPrefix)){
                // Not Bearer Token
                errorMsg = "토큰은 Bearer 로 시작해야 합니다."
                authorized = false
            }else {
                try{
                    // Verify
                    val jwt = token.replace(bearerPrefix, "")
                    claims = JwtUtil.getClaimsFromToken(jwt)

                    val userName = claims["sub"] as String
                    val jwtUser = userDetailsService.loadUserByUsername(userName)

                    SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(jwtUser,null,jwtUser.authorities)
                } catch (ex: ExpiredJwtException) {
                    errorMsg = "토큰이 만료되었습니다."
                    authorized = false
                } catch(ex: Exception){
                    errorMsg = "유효한 토큰이 아닙니다."
                    authorized = false
                }
            }
        }

        if(authorized) {
            filterChain.doFilter(request, response)
        }else {
            val res = mutableMapOf<String, Any>()
            res["status"] = HttpStatus.UNAUTHORIZED.value()
            res["message"] = errorMsg

            response.status = HttpStatus.UNAUTHORIZED.value()
            response.characterEncoding = "UTF-8"
            val objectMapper = ObjectMapper()
            response.writer.println(objectMapper.writeValueAsString(res))
            response.writer.flush()
        }
    }
}