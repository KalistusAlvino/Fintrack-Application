package com.example.fintrack.data.remote.dto.user

import com.google.gson.annotations.SerializedName

data class DataWalletResponseDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("username")
    val username: String,

    @SerializedName("balance")
    val balance: String
)
