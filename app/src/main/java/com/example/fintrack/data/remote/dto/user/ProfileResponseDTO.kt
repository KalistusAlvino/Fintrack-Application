package com.example.fintrack.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class ProfileResponseDTO(
    @SerializedName("username")
    val username: String,

    @SerializedName("email")
    val email: String,
)
