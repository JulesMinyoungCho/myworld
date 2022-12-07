package com.my.api.config

import com.my.api.rest.v1.entity.DataType
import com.my.api.rest.v1.repository.DataTypeRepository
import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
class MockInitializer (
    val dataTypeRepository: DataTypeRepository
) {
    @PostConstruct
    fun postConstruct() {
        val dataType = DataType()
        dataType.typeName = "데이터 타입 #1"
        dataTypeRepository.save(dataType)
    }
}