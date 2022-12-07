package com.my.api.common.util

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.GrantedAuthority
import java.util.*

class JwtUtil {
    companion object {
        private const val secret = "abcde12345abcde12345abcde1234512"
        private const val expiration = 60*60
        fun generateToken(subject: String, rls: List<GrantedAuthority>) : String{
            val claims = mutableMapOf<String, Any>()
            claims["sub"] = subject
            claims["rls"] = rls
            return generateTokenWithClaims(claims)
        }
        fun getClaimsFromToken(token:String) : Map<String, Any> {
            return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .body
        }

        private fun generateTokenWithClaims(claims: Map<String, Any>) : String{
            val now = Date()
            val exp = Date(now.time + expiration*1000)

            return Jwts.builder()
                .setSubject(claims["sub"] as String)
                .setIssuedAt(now)
                .setExpiration(exp)
                .addClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
        }
    }
}