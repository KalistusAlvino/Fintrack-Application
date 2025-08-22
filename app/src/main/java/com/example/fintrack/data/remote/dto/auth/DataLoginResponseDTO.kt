package com.example.fintrack.data.remote.dto.auth

import com.google.gson.annotations.SerializedName

data class DataLoginResponseDTO(

    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String,

    @SerializedName("token")
    val token: String
)
