package com.my.api.security

import com.my.api.rest.v1.entity.User
import com.my.api.rest.v1.repository.QUserRepository
import com.my.api.rest.v1.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailsService(
    private val userRepository: UserRepository,
    private val qUserRepository: QUserRepository,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findById(username).orElse(User())

        return JwtUser(user.userId , ""
            , user.roles
                .map{ GrantedAuthority{ it.role?.roleCode } }
                .toMutableList()
        )
    }
}