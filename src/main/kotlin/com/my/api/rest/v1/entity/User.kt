package com.my.api.rest.v1.entity

import com.my.api.common.entity.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "t_user")
class User : BaseAuditEntity() {
    @Id
    @Column(name = "user_id")
    var userId: String = ""

    @Column(name = "name")
    var name: String? = ""

    @Column(name = "password")
    var password: String? = ""

    @OneToMany(mappedBy = "user" , orphanRemoval = true , fetch = FetchType.EAGER)
    @OrderBy("id asc")
    var roles: MutableList<UserRoleMap> = mutableListOf()

}