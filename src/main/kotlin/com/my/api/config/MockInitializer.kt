package com.my.api.config

import com.my.api.rest.v1.repository.RoleRepository
import com.my.api.rest.v1.repository.UserRepository
import com.my.api.rest.v1.repository.UserRoleRepository
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class MockInitializer(
    val userRepository: UserRepository,
    val roleRepository: RoleRepository,
    val userRoleRepository: UserRoleRepository
) {

    @PostConstruct
    fun init() {
//        val user = User()
//
//        user.userId = "jules.my"
//        user.name = "minyoung"
//        user.password = "minyoung"
//
//        val guestUser = User()
//        guestUser.userId = "guest"
//        guestUser.password = "password"
//        guestUser.name = "guest"
//
//        val role1 = Role()
//        role1.roleCode = "SYS_ADMIN"
//        role1.roleName = "System Administrator"
//
//        val role2 = Role()
//        role2.roleCode = "GUEST"
//        role2.roleName = "Guest User"
//
//        roleRepository.saveAll(listOf(role1, role2))
//
//        userRepository.saveAll(listOf(user, guestUser))
//
//        val map1 = UserRoleMap()
//        map1.user = user
//        map1.role = role1
//
//        val map2 = UserRoleMap()
//        map2.user = guestUser
//        map2.role = role2
//
//        userRoleRepository.save(map1)
//        userRoleRepository.saveAll(listOf(map1, map2))
    }
}