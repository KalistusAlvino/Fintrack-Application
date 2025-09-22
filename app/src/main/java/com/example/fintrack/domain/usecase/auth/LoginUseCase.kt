package com.example.fintrack.domain.usecase.auth

import com.example.fintrack.core.common.ResultState
import com.example.fintrack.di.model.auth.LoginResponse
import com.example.fintrack.domain.di.auth.LoginRepository
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
) {
    operator fun invoke(
        username: String,
        password: String,
    ): Flow<ResultState<LoginResponse>> = flow {
        emit(ResultState.Loading())
        try {
            val result = loginRepository.login(username, password)
            loginRepository.saveSession(result.id?.toInt() ?: 0,result.username.toString(), result.token.toString())
            emit(ResultState.Success(data = result))
        } catch (e: HttpException) {
            val errorBody = e.response()?.errorBody()?.string()
            val errorResponse = Gson().fromJson(errorBody, LoginResponse::class.java)
            emit(ResultState.Error(message = errorResponse.message))
        } catch (e: Exception) {
            emit(ResultState.Error(message = e.message))
        }
    }.flowOn(Dispatchers.IO)
}