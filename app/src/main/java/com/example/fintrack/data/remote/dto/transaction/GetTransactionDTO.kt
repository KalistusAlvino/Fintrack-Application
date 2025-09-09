package com.example.fintrack.data.remote.dto.transaction

import com.google.gson.annotations.SerializedName

data class GetTransactionDTO(
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("images")
    val images: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("date")
    val date: String,

    @SerializedName("amount")
    val amount: Long,

    @SerializedName("formatted_amount")
    val formattedAmount: String,
)
