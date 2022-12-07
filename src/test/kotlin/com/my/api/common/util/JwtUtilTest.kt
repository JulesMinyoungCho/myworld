package com.my.api.common.util

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.security.core.GrantedAuthority

internal class JwtUtilTest {

    @Test
    fun `토큰이 정상적으로 생성 된다`() {

        val token = JwtUtil.buildJwt("jules.my", mutableListOf(GrantedAuthority { "SYS_ADMIN" }))
        assertNotNull(token)
    }


    @Test
    fun `생성 된 토큰에서 claim을 다시 뽑을 수 있다`() {
        val token = JwtUtil.buildJwt("jules.my", mutableListOf(GrantedAuthority { "SYS_ADMIN" }))
        val claims = JwtUtil.getClaims(token)
        assertNotNull(claims)
    }


}