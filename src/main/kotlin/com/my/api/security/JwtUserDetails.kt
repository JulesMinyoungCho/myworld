package com.my.api.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class JwtUserDetails : UserDetailsService{
    override fun loadUserByUsername(username: String?): UserDetails {
        return JwtUser("jules.my", "", mutableListOf(
            GrantedAuthority { "SYS_ADMIN" }
        ))
    }
}