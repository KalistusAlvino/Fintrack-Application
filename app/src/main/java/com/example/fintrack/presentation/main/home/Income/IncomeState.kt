package com.example.fintrack.presentation.main.home.Income

import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.di.model.income.get.IncomeResponse
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.di.model.income.get.ThisMonthIncomeResponse

data class IncomeState(
    val success: Boolean = false,
    val isLoading: Boolean = false,
    val data: List<IncomeResponse>? = null,
    val thisMonthIncome: ThisMonthIncomeResponse? = null,
    val monthlyIncome: List<MonthlyIncomeResponse>? = null,
    val incomeCategory: List<IncomeCategoryResponse>? = null,
    var message: String = ""
)