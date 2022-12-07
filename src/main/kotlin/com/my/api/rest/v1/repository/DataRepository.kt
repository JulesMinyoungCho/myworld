package com.my.api.rest.v1.repository

import com.my.api.rest.v1.entity.Data
import com.my.api.rest.v1.entity.QData.data
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

interface DataRepository : CrudRepository<Data, Long>

@Repository
class QDataRepository (
    private val queryFactory: JPAQueryFactory
){
    fun selectAll(): List<Data>
        = queryFactory.selectFrom(data)
        .fetch()

}