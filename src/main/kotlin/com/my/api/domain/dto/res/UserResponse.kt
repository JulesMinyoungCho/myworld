package com.my.api.domain.dto.res

data class UserResponse (
    var userId: String?,
    var name: String?,
    var test: String?,
) {
    constructor() : this("","", "")
}