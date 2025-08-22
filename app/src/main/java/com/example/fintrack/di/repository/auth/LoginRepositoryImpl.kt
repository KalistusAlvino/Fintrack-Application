package com.example.fintrack.di.repository.auth

import com.example.fintrack.core.common.toLoginResponse
import com.example.fintrack.data.pref.UserPreference
import com.example.fintrack.data.remote.FintrackApi
import com.example.fintrack.di.model.auth.LoginResponse
import com.example.fintrack.di.model.user.User
import com.example.fintrack.domain.di.auth.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val fintrackApi: FintrackApi,
    private val userPreference: UserPreference
) : LoginRepository {
    override suspend fun login(
        username: String,
        password: String
    ): LoginResponse {
        return fintrackApi.login(username, password).toLoginResponse()
    }

    override suspend fun saveSession(
        id: Int,
        username: String,
        token: String
    ) {
        userPreference.saveSession(
            User(
                id = id,
                username = username,
                token = token,
                isLogin = true
            )
        )
    }


}