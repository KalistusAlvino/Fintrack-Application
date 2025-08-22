package com.example.fintrack.domain.di.income

import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.di.model.income.get.IncomeResponse
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.di.model.income.get.ThisMonthIncomeResponse
import com.example.fintrack.di.model.income.post.PostIncomeResponse

interface IncomeRepository {
    suspend fun getIncome(): List<IncomeResponse>

    suspend fun getThisMonthIncome(): ThisMonthIncomeResponse

    suspend fun getMonthlyIncome(): List<MonthlyIncomeResponse>

    suspend fun getIncomeCategory(): List<IncomeCategoryResponse>

    suspend fun postIncome(categoryId: Int, amount: Long, description: String): PostIncomeResponse
}