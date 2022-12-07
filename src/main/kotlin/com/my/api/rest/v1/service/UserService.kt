package com.my.api.rest.v1.service

import com.my.api.common.util.JwtUtil
import com.my.api.domain.dto.res.LoginResponse
import com.my.api.rest.v1.entity.User
import com.my.api.rest.v1.entity.UserRoleMap
import com.my.api.rest.v1.mapper.UserMapper
import com.my.api.rest.v1.repository.QUserRepository
import com.my.api.rest.v1.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.stereotype.Service

@Service
class UserService(
    val userRepository: UserRepository,
    val qUserRepository: QUserRepository,
    val userMapper: UserMapper
) {

    fun selectAllUser(): MutableList<User> {
        return qUserRepository.selectUsers()
    }

    fun login(id: String, password: String): LoginResponse {
        val user = qUserRepository.login(id, password) ?: return LoginResponse("id", null)

        val loginResponse = userMapper.entityToLoginRes(user)
        loginResponse.token = JwtUtil.generateToken(id, rolesToRls(user.roles))

        return loginResponse
    }

    fun rolesToRls(roles: List<UserRoleMap>)
        = roles.map {
            GrantedAuthority {it.role?.roleCode}
        }.toList()
}