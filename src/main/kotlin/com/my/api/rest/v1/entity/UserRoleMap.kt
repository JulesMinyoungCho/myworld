package com.my.api.rest.v1.entity

import com.my.api.common.entity.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "t_user_role_map")
class UserRoleMap : BaseAuditEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id" )
    var user: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_code" , referencedColumnName = "role_code")
    var role: Role? = null
}