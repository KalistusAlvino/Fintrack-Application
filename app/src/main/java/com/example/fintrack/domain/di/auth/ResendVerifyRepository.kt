package com.example.fintrack.domain.di.auth

import com.example.fintrack.di.model.auth.RegisterResponse
import com.example.fintrack.di.model.auth.ResendVerifiyResponse

interface ResendVerifyRepository {
    suspend fun resendVerify(email: String) : ResendVerifiyResponse
}