package com.my.api.rest.v1.mapper

import com.my.api.rest.v1.entity.User
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class UserMapperTest {

    @Autowired
    private lateinit var mapper: UserMapper

    @Test
    fun entityToRes() {

        val entity = User()
        entity.userId = "my id"
        entity.name = "minyoung"
        entity.password = "my pw"

        val res = mapper.entityToRes(entity)

        assertEquals(entity.userId, res.userId)
        assertEquals(entity.name, res.name)
        assertEquals(entity.password, res.test)

    }
}