package com.my.api.rest.v1.repository

import ch.qos.logback.core.read.ListAppender
import com.my.api.rest.v1.entity.Role
import com.my.api.rest.v1.entity.User
import com.my.api.rest.v1.entity.UserRoleMap
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import javax.persistence.EntityManager
import javax.transaction.Transactional

@SpringBootTest
internal class UserRepositoryTest {

    @Autowired private lateinit var userRepository: UserRepository
    @Autowired private lateinit var qUserRepository: QUserRepository
    @Autowired private lateinit var roleRepository: RoleRepository
    @Autowired private lateinit var userRoleRepository: UserRoleRepository
    @Autowired private lateinit var em: EntityManager

    private lateinit var appender: ListAppender<String>

    @Test
    fun findByIdOrNullTest() {
        val user = userRepository.findById("jules.my").orElse(User())


        println(user)
    }

    @Test
    @Transactional
    fun saveTest() {
        val user = User()

        user.userId = "jules.my"
        user.name = "minyoung"
        user.password = "minyoung"

        val guestUser = User()
        guestUser.userId = "guest"
        guestUser.password = "password"
        guestUser.name = "guest"

        val role1 = Role()
        role1.roleCode = "SYS_ADMIN"
        role1.roleName = "System Administrator"

        val role2 = Role()
        role2.roleCode = "GUEST"
        role2.roleName = "Guest User"

        roleRepository.saveAll(listOf(role1, role2))

        userRepository.saveAll(listOf(user, guestUser))

        val map1 = UserRoleMap()
        map1.user = user
        map1.role = role1

        val map2 = UserRoleMap()
        map2.user = guestUser
        map2.role = role2

//        userRoleRepository.save(map1)
        userRoleRepository.saveAll(listOf(map1, map2))
    }

    @Test
    fun loginTest(){
        val user = qUserRepository.login("jules.my", "minyoung")

        Assertions.assertNotNull(user)


        println()

    }

    @Test
    fun selectUserByIdTest() {

        val result = qUserRepository.selectUserById("jules.my")

        println(result)
//        appender = ListAppender()
//        appender.

    }

    @Test
    fun selectUsersTest() {
        val result = qUserRepository.selectUsers()

        println(result)

    }

}