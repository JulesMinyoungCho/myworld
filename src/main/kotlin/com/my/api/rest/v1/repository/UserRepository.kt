package com.my.api.rest.v1.repository

import com.my.api.rest.v1.entity.QUser.user
import com.my.api.rest.v1.entity.User
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.util.*

interface UserRepository : CrudRepository<User, String> {

    @EntityGraph(attributePaths = ["roles"], type = EntityGraph.EntityGraphType.LOAD)
    override fun findById(id: String): Optional<User>
}

@Repository
class QUserRepository (val queryFactory: JPAQueryFactory) {

    fun login(id: String, password: String): User?
        = queryFactory.selectFrom(user)
        .where(user.userId.eq(id)
            .and(user.password.eq(password)))
        .fetchOne()

    fun selectUserById(id: String): User?
        = queryFactory.select(user)
        .from(user)
        .where(user.userId.eq(id))
        .fetchOne()

    fun selectUsers(): MutableList<User>
        = queryFactory.select(user)
        .from(user)
        .fetch()

}