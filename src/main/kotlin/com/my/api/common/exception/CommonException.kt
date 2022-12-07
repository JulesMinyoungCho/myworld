package com.my.api.common.exception

import org.springframework.http.HttpStatus

open class CommonException (
    private val status: HttpStatus,
    override val message: String?,
) : RuntimeException( message ?: status.reasonPhrase )

class UserNotFoundException: CommonException(HttpStatus.BAD_REQUEST , "사용자를 찾을 수 없습니다.")
class UnAuthorizedException: CommonException(HttpStatus.UNAUTHORIZED, "인증 되지 않은 요청입니다.")
