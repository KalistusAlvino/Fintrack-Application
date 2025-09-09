package com.example.fintrack.presentation.main.home.transaction

import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.di.model.Transaction.GetThisMonthSummaryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.di.model.Transaction.PostTransactionResponse

data class TransactionState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    var message: String = "",
    val postIncome: PostTransactionResponse? = null,
    val postExpenses: PostTransactionResponse? = null,
    val expenses: List<GetTransactionResponse>? = null,
    val income: List<GetTransactionResponse>? = null,
    val expensesCategory: List<GetTransactionCategoryResponse>? = null,
    val incomeCategory: List<GetTransactionCategoryResponse>? = null,
    val thisMonthIncome: GetThisMonthSummaryResponse? = null,
    val thisMonthExpenses: GetThisMonthSummaryResponse? = null,
    val monthlyIncome: List<GetMonthlySummaryResponse>? = null,
    val monthlyExpenses: List<GetMonthlySummaryResponse>? = null
)
