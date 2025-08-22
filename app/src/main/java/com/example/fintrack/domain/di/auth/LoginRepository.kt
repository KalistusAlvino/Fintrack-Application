package com.example.fintrack.domain.di.auth

import com.example.fintrack.di.model.auth.LoginResponse

interface LoginRepository {
    suspend fun login(username: String, password: String): LoginResponse
    suspend fun saveSession(id:Int,username: String, token: String)
}