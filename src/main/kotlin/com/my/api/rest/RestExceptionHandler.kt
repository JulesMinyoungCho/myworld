package com.my.api.rest

import com.my.api.common.exception.CommonException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestExceptionHandler {

    val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ExceptionHandler(CommonException::class)
    fun handle(ex: CommonException) : ResponseEntity<Any>{
        logger.error("CommonException 발생 :: ",ex)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("서버 오류가 발생했습니다. 관리자에게 문의하세요. msg = ${ex.message}")
    }

    @ExceptionHandler(ConstraintViolationException::class)
    fun handle(ex: ConstraintViolationException) : ResponseEntity<Any>{
        logger.error("ConstraintViolationException 발생 :: ",ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("잘못된 요청입니다 (ConstraintViolation). msg = ${ex.message}")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handle(ex: MethodArgumentNotValidException) : ResponseEntity<Any>{
        logger.error("MethodArgumentNotValidException 발생 :: ",ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("잘못된 요청입니다 (MethodArgumentNotValid). msg = ${ex.message}")
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handle(ex: HttpMessageNotReadableException) : ResponseEntity<Any>{
        logger.error("HttpMessageNotReadableException 발생 :: ",ex)
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body("잘못된 요청입니다 (HttpMessageNotReadable). msg = ${ex.message}")
    }
}