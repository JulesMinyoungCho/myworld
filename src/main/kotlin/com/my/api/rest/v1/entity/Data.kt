package com.my.api.rest.v1.entity

import com.my.api.common.entity.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "t_data")
class Data : BaseAuditEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0L

    @Column(name = "key")
    var key: String? = ""

    @Column(name = "value")
    var value: String? = ""

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type_id")
    var type: DataType? = null

}