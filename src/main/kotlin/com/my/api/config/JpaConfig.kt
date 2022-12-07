package com.my.api.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import java.util.*
import javax.persistence.EntityManager

@Configuration
@EnableJpaAuditing
class JpaConfig : AuditorAware<String>{
    override fun getCurrentAuditor(): Optional<String> {
        return Optional.ofNullable("test")
    }

    @Bean
    fun queryFactory(entityManager: EntityManager)
        = JPAQueryFactory(entityManager)
}