package com.my.api.common.dto

data class CommonResponse<T> (
    val body: T,
    val status: Int,
    val message: String
)