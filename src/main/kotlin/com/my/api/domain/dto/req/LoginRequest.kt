package com.my.api.domain.dto.req

import javax.validation.constraints.NotBlank

data class LoginRequest (
    @field:NotBlank var id: String?,
    @field:NotBlank var password: String?,
)
