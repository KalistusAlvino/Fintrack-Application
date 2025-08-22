package com.example.fintrack.domain.usecase.register

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.domain.di.auth.AuthRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    operator fun invoke(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) : Flow<ResultState<RegisterResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = authRepository.register(username, email, password, confirmPassword)
            emit(ResultState.Success(data = result))
        } catch  (e : HttpException){
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, RegisterResponse::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}