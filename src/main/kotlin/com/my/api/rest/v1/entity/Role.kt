package com.my.api.rest.v1.entity

import com.my.api.common.entity.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "t_role")
class Role : BaseAuditEntity() {

    @Id
    @Column(name = "role_code")
    var roleCode: String = ""

    @Column(name = "role_name")
    var roleName : String = ""

    @OneToMany(mappedBy = "role" , orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("id asc")
    var users: MutableList<UserRoleMap> = mutableListOf()
}