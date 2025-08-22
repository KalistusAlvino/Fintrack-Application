package com.example.fintrack.data.remote.dto.income.get

import com.google.gson.annotations.SerializedName

data class DataIncomeCategoryDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val imageUrl: String,
)
