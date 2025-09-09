package com.example.fintrack.domain.di.transaction

import com.example.fintrack.di.model.Transaction.GetMonthlySummaryResponse
import com.example.fintrack.di.model.Transaction.GetThisMonthSummaryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionCategoryResponse
import com.example.fintrack.di.model.Transaction.GetTransactionResponse
import com.example.fintrack.di.model.Transaction.PostTransactionResponse

interface TransactionRepository {
    suspend fun getIncome(): List<GetTransactionResponse>

    suspend fun getExpenses(): List<GetTransactionResponse>

    suspend fun getExpensesCategory(): List<GetTransactionCategoryResponse>

    suspend fun getIncomeCategory(): List<GetTransactionCategoryResponse>

    suspend fun postIncome(categoryId: Int, amount: Long, description: String): PostTransactionResponse

    suspend fun postExpenses(categoryId: Int, amount: Long, description: String): PostTransactionResponse

    suspend fun getThisMonthIncome(): GetThisMonthSummaryResponse

    suspend fun getThisMonthExpenses(): GetThisMonthSummaryResponse

    suspend fun getMonthlyIncome(): List<GetMonthlySummaryResponse>

    suspend fun getMonthlyExpenses(): List<GetMonthlySummaryResponse>
}