package com.my.api.rest.v1.controller

import com.my.api.common.util.JwtUtil
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.security.core.GrantedAuthority
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest
internal class HealthCheckControllerTest {

    @Autowired private lateinit var mockMvc: MockMvc

    @Test
    fun healthCheck() {
        val token = JwtUtil.generateToken("test1", listOf(GrantedAuthority{"SYS_ADMIN"}))
        val uri = "/v1/health"
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
            .contentType(MediaType.APPLICATION_JSON)
            .header("Authorization" , "Bearer $token")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk)
        .andDo(MockMvcResultHandlers.print())
    }
}