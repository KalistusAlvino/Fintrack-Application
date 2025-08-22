package com.example.fintrack.presentation.main.home.transaction

import com.example.fintrack.di.model.income.post.PostIncomeResponse

data class TransactionState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    var message: String = "",
    val data: PostIncomeResponse? = null,
)
