package com.example.fintrack.data.remote.dto.transaction

import com.google.gson.annotations.SerializedName

data class GetTransactionCategoryDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val imageUrl: String,
)
