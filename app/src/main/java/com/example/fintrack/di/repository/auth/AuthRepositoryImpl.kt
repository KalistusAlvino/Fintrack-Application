package com.example.fintrack.di.repository.auth

import com.example.fintrack.core.common.toRegisterResponse
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.domain.di.auth.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val fintrackApi: FintrackApi): AuthRepository {
    override suspend fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ): RegisterResponse {
        return fintrackApi.register(username, email, password, confirmPassword).toRegisterResponse()
    }
}



