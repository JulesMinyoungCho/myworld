package com.my.api.rest.v1.repository

import com.my.api.rest.v1.entity.DataType
import org.springframework.data.repository.CrudRepository

interface DataTypeRepository : CrudRepository<DataType, Long>