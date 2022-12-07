package com.my.api.rest.v1.service

import com.my.api.rest.v1.domain.req.DataRequest
import com.my.api.rest.v1.domain.res.DataResponse
import com.my.api.rest.v1.mapper.DataMapper
import com.my.api.rest.v1.repository.DataRepository
import com.my.api.rest.v1.repository.DataTypeRepository
import com.my.api.rest.v1.repository.QDataRepository
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class DataService(
    private val dataMapper: DataMapper,
    private val dataRepository: DataRepository,
    private val dataTypeRepository: DataTypeRepository,
    private val qDataRepository: QDataRepository,
) {

    fun createData(dataRequest: DataRequest): DataResponse {

        val entity = dataMapper.reqToEntity(dataRequest)

        entity.type = dataTypeRepository.findByIdOrNull(1L)

        val saved = dataRepository.save(entity)

        return dataMapper.entityToRes(saved)

    }

    @Cacheable(value=["data"])
    fun getData(): List<DataResponse> {

        val dataList = qDataRepository.selectAll()

        return dataList.map {
            dataMapper.entityToRes(it)
        }.toList()
    }

}