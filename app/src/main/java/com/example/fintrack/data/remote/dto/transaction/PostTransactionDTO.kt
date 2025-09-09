package com.example.fintrack.data.remote.dto.transaction

import com.google.gson.annotations.SerializedName

data class PostTransactionDTO(
    @SerializedName("wallet_id")
    val walletId: Int,
    @SerializedName("category_id")
    val categoryId: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("amount")
    val amount: String,
    @SerializedName("updated_at")
    val updatedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("id")
    val id: Int
)
