package com.example.fintrack.data.remote.dto.income.get

import com.google.gson.annotations.SerializedName

data class DataThisMonthIncomeDTO(
    @SerializedName("amount")
    val amount: Long,

    @SerializedName("formatted_total_amount")
    val formattedAmount: String,
)
