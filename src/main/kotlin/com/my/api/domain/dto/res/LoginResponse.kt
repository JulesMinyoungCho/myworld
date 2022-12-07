package com.my.api.domain.dto.res

data class LoginResponse (
    var userId: String,
    var token: String?,
)