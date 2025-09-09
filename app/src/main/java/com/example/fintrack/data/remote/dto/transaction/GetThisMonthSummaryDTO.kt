package com.example.fintrack.data.remote.dto.transaction

import com.google.gson.annotations.SerializedName

data class GetThisMonthSummaryDTO(
    @SerializedName("total_amount")
    val amount: Long,

    @SerializedName("formatted_total_amount")
    val formattedAmount: String,
)
