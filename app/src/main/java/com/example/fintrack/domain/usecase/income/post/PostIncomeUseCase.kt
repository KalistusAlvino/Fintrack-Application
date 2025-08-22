package com.example.fintrack.domain.usecase.income.post

import android.util.Log
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.data.remote.dto.income.post.PostIncomeDTO
import com.example.fintrack.di.model.income.post.PostIncomeResponse
import com.example.fintrack.domain.di.income.IncomeRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class PostIncomeUseCase @Inject constructor(
    private val incomeRepository: IncomeRepository
) {
    operator fun invoke(
        categoryId: Int,
        amount: Long,
        description: String
    ): Flow<ResultState<PostIncomeResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = incomeRepository.postIncome(categoryId, amount, description)
            emit(ResultState.Success(data = result))

        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, PostIncomeDTO::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}