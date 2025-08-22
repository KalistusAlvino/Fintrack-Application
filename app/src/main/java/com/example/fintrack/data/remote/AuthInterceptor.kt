package com.example.fintrack.data.remote

import com.example.fintrack.data.pref.UserPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val userPreference: UserPreference
): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = runBlocking {
            userPreference.getSession().first().token
        }
        val request = chain.request().newBuilder()
            .addHeader("Accept","application/json")
            .addHeader("Authorization", "Bearer $token")
            .build()
        return chain.proceed(request)
    }
}