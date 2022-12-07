package com.my.api.common.util

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.security.core.GrantedAuthority

internal class JwtUtilTest {

    @Test
    fun generateTokenTest() {

        val result = JwtUtil.generateToken("my",listOf(GrantedAuthority{"test"}))

        assertNotNull(result)
        println(result)

    }

    @Test
    fun getClaimsTest() {

        val result = JwtUtil.generateToken("my",listOf(GrantedAuthority{"test"}))

        val claims = JwtUtil.getClaimsFromToken(result)

        println(claims)

    }


}