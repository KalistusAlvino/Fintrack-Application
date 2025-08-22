package com.example.fintrack.data.remote.dto.income.get

import com.google.gson.annotations.SerializedName

data class DataMonthlyIncomeDTO(
    @SerializedName("month_name")
    val monthName: String,

    @SerializedName("month_key")
    val monthKey: String,

    @SerializedName("total_income")
    val totalIncome: Long,

    @SerializedName("formatted_income")
    val formattedIncome: String,
)
