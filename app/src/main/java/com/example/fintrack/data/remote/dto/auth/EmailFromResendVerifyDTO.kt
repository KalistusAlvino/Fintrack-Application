package com.example.fintrack.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class EmailFromResendVerifyDTO(
    @SerializedName("email")
    val email: String? = null
)
