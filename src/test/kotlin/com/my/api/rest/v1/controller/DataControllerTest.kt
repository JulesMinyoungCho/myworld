package com.my.api.rest.v1.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.my.api.rest.v1.domain.req.DataRequest
import com.my.api.rest.v1.domain.res.DataResponse
import com.my.api.rest.v1.service.DataService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@ExtendWith(SpringExtension::class)
@WebMvcTest(DataController::class)
internal class DataControllerTest {

    @TestConfiguration
    class TestConfig {
        @Bean
        fun dataService() = mockk<DataService>()
    }

    private val objectMapper = jacksonObjectMapper()

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var dataService: DataService

    private val mockDataResponse = mockk<DataResponse>{
        every { id } returns "id 1"
        every { key } returns "key 1"
        every { value } returns "value 1"
        every { typeName } returns "typeName 1"
    }

    @Test
    fun createData() {
        every { dataService.createData(any()) } returns mockDataResponse

        val dataRequest = DataRequest("aa", "a1a1")
        val reqStr = objectMapper.writeValueAsString(dataRequest)
        val uri = "/v1/data"

        mockMvc.perform(
            MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", "")
                .content(reqStr)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getData() {

        every { dataService.getData() } returns listOf(mockDataResponse)

        val uri = "/v1/data"

        val result = mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .header("Authorization", "")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
            .andReturn()

        val resultMap = objectMapper.readValue(result.response.contentAsString , Map::class.java)

        println(resultMap)
    }
}
