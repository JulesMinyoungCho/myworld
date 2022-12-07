package com.my.api.rest.v1.mapper

import com.my.api.domain.dto.res.LoginResponse
import com.my.api.domain.dto.res.UserResponse
import com.my.api.rest.v1.entity.User
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.ReportingPolicy

@Mapper(componentModel = "spring" , unmappedSourcePolicy = ReportingPolicy.IGNORE , unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class UserMapper {

    @Mappings(*[
        Mapping(source = "password", target = "test")
    ])
    abstract fun entityToRes(entity: User) : UserResponse


    @Mappings(*[
        Mapping(ignore = true, target = "token")
    ])
    abstract fun entityToLoginRes(entity: User) : LoginResponse


}