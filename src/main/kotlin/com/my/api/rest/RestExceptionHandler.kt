package com.my.api.rest

import com.my.api.common.exception.CommonException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class RestExceptionHandler : BaseRestController() {


    @ExceptionHandler(CommonException::class)
    fun handle( ex: CommonException ) : ResponseEntity<Any> {
        return response(
            "Error"
        )
    }

}