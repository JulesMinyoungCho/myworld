package com.my.api.common.entity

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
class BaseAuditEntity {

    @CreatedDate
    @Column(name = "created_at" , updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()

    @CreatedBy
    @Column(name = "created_by" , updatable = false)
    var createdBy: String = "system"

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @LastModifiedBy
    @Column(name = "updated_by")
    var updatedBy: String = "system"

}