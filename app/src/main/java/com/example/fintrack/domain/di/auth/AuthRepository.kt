package com.example.fintrack.domain.di.auth

import com.example.fintrack.data.remote.dto.base.BaseResponse
import com.example.fintrack.di.model.auth.RegisterResponse

interface AuthRepository {
    suspend fun register(username: String, email: String, password: String, confirmPassword: String) : RegisterResponse

    suspend fun logout(): BaseResponse<Nothing>

    suspend fun clearSession()
}