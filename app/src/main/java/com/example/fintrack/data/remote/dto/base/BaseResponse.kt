package com.example.fintrack.data.remote.dto.base

data class BaseResponse<T>(
    val success: Boolean? = null,
    val message: String? = null,
    val data: T? = null
)
