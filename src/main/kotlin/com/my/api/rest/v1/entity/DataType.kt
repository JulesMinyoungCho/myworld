package com.my.api.rest.v1.entity

import com.my.api.common.entity.BaseAuditEntity
import javax.persistence.*

@Entity
@Table(name = "t_data_type")
class DataType : BaseAuditEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long = 0L

    @Column(name = "type_name")
    var typeName: String? = ""

    @OneToMany(mappedBy = "type")
    var dataList: MutableList<Data> = mutableListOf()

}