package com.example.fintrack.domain.usecase.resendVerify

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.auth.ResendVerifiyResponse
import com.example.fintrack.domain.di.auth.ResendVerifyRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class ResendVerifyUseCase @Inject constructor(
    private val resendVerifyRepository: ResendVerifyRepository
){
    operator fun invoke(
        email: String,
    ): Flow<ResultState<ResendVerifiyResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = resendVerifyRepository.resendVerify(email)
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.toString()
            val errorResponse = Gson().fromJson(errorBody, ResendVerifiyResponse::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}