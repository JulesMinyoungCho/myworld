package com.my.api.rest

import com.my.api.common.dto.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class BaseRestController {
    open fun response(data: Any) : ResponseEntity<Any>
        = ResponseEntity.ok(
            CommonResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.reasonPhrase,
                data
            )
        )
}