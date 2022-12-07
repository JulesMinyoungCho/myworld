package com.my.api.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class JwtUser(
    private val id: String,
    private val password: String,
    private val authorities: MutableList<GrantedAuthority>,
) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.id
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}