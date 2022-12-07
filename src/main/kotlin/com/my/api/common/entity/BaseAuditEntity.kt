package com.my.api.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import javax.persistence.MappedSuperclass
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
open class BaseAuditEntity : Serializable{
    @CreatedDate
    @Column(name = "created_at" , columnDefinition = "timestamp default CURRENT_TIMESTAMP" , updatable = false)
    open var createdAt: LocalDateTime? = LocalDateTime.now()

    @CreatedBy
    @Column(name = "created_by" , updatable = false)
    open var createdBy: String? = "system"

    @LastModifiedDate
    @Column(name = "updated_at" , columnDefinition = "timestamp default CURRENT_TIMESTAMP")
    open var updatedAt: LocalDateTime? = LocalDateTime.now()

    @LastModifiedBy
    @Column(name = "updated_by")
    open var updatedBy: String? = "system"
}