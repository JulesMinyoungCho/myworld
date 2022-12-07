package com.my.api.rest.v1.mapper

import com.my.api.rest.v1.domain.req.DataRequest
import com.my.api.rest.v1.domain.res.DataResponse
import com.my.api.rest.v1.entity.Data
import org.mapstruct.*

@Mapper(componentModel = "spring" , unmappedSourcePolicy = ReportingPolicy.IGNORE, unmappedTargetPolicy = ReportingPolicy.IGNORE)
abstract class DataMapper {

    @Mappings(*[
        Mapping(source = "key", target = "key"),
        Mapping(source = "value", target = "value" , qualifiedByName = ["convertValue"]),
        Mapping(source = "type.typeName", target = "typeName"),
    ])
    abstract fun entityToRes(entity: Data) : DataResponse

    abstract fun reqToEntity(req: DataRequest) : Data

    @Named("convertValue")
    protected fun convertValue(str:String)
        = "$str convert"


}