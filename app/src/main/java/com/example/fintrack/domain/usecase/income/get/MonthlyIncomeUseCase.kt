package com.example.fintrack.domain.usecase.income.get

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.income.get.MonthlyIncomeResponse
import com.example.fintrack.domain.di.income.IncomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class MonthlyIncomeUseCase @Inject constructor(
    private val incomeRepository: IncomeRepository,
) {
    operator fun invoke(
    ): Flow<ResultState<List<MonthlyIncomeResponse>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = incomeRepository.getMonthlyIncome()
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            emit(ResultState.Error(message = e.message.toString()))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message.toString()))
        }
    }
}