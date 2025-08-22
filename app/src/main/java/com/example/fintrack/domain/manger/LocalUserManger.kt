package com.example.fintrack.domain.manger

import kotlinx.coroutines.flow.Flow

interface LocalUserManger {
    suspend fun saveLoginStatus()
    fun  readLoginStatus(): Flow<Boolean>

    suspend fun saveOnBoardingStatus()
    fun  readOnBoardingStatus(): Flow<Boolean>

}