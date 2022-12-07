package com.my.api.common.dto

import java.io.Serializable

class CommonResponse<T> (
    val status: Int,
    val message: String,
    val data: T,
) : Serializable