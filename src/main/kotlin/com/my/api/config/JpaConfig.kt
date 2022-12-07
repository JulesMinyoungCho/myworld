package com.my.api.config

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.AuditorAware
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.core.context.SecurityContextHolder
import java.util.*
import javax.persistence.EntityManager

@Configuration
@EnableJpaAuditing
class JpaConfig : AuditorAware<String>{
    override fun getCurrentAuditor(): Optional<String> {
        val authentication = SecurityContextHolder.getContext().authentication
        return Optional.ofNullable(if (authentication == null) "system" else authentication.name)
    }

    @Bean
    fun jpaQueryFactory(em: EntityManager?)
        = JPAQueryFactory(em)
}