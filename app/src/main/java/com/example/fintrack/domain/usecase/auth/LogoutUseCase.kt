package com.example.fintrack.domain.usecase.auth

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.di.model.auth.LoginResponse
import com.example.fintrack.domain.di.auth.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(): Flow<ResultState<BaseResponse<Nothing>>> = flow {
        emit(ResultState.Loading())
        try {
            val result = authRepository.logout()
            authRepository.clearSession()
            emit(ResultState.Success(data = result))
        }catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, BaseResponse::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }
}