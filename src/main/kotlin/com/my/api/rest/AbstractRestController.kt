package com.my.api.rest

import com.my.api.common.dto.CommonResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class AbstractRestController {
    fun response(data: Any) : ResponseEntity<Any>
        = ResponseEntity.ok(
            CommonResponse(
                data,
                HttpStatus.OK.value(),
                HttpStatus.OK.reasonPhrase
            )
        )

}