package com.example.fintrack.data.remote.dto.transaction

import com.google.gson.annotations.SerializedName

data class GetMonthlySummaryDTO(
    @SerializedName("month_name")
    val monthName: String,

    @SerializedName("month_key")
    val monthKey: String,

    @SerializedName("total")
    val total: Long,

    @SerializedName("formatted_total")
    val formattedTotal: String,
)
