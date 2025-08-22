package com.example.fintrack.di.repository.income

import com.example.fintrack.core.common.toIncomeResponse
import com.example.fintrack.core.common.toMonthlyIncomeResponse
import com.example.fintrack.core.common.toThisMonthIncomeResponse
import com.example.fintrack.core.common.toIncomeCategoryResponse
import com.example.fintrack.core.common.toPostIncomeResponse
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.data.remote.dto.income.post.PostIncomeDTO
import com.example.fintrack.di.model.income.get.IncomeCategoryResponse
import com.example.fintrack.di.model.income.get.IncomeResponse
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.di.model.income.get.ThisMonthIncomeResponse
import com.example.fintrack.di.model.income.post.PostIncomeResponse
import com.example.fintrack.domain.di.income.IncomeRepository
import javax.inject.Inject

class IncomeRepositoryImpl @Inject constructor(
    private val fintrackApi: FintrackApi
): IncomeRepository{
    override suspend fun getIncome(): List<IncomeResponse> {
        return fintrackApi.getIncome().data.map { it.toIncomeResponse() }
    }

    override suspend fun getThisMonthIncome(): ThisMonthIncomeResponse {
        return fintrackApi.thisMonthIncome().data.toThisMonthIncomeResponse()
    }

    override suspend fun getMonthlyIncome(): List<MonthlyIncomeResponse> {
        return fintrackApi.monthlyIncome().data.map { it.toMonthlyIncomeResponse() }
    }

    override suspend fun getIncomeCategory(): List<IncomeCategoryResponse> {
        return fintrackApi.incomeCategory().data.map { it.toIncomeCategoryResponse() }
    }

    override suspend fun postIncome(
        categoryId: Int,
        amount: Long,
        description: String
    ): PostIncomeResponse {
        return fintrackApi.postIncome(categoryId, amount, description).toPostIncomeResponse()
    }

}




