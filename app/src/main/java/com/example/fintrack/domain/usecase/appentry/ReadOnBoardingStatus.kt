package com.example.fintrack.domain.usecase.appentry

import com.example.fintrack.domain.manger.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadOnBoardingStatus(
    private val localUserManger: LocalUserManger
) {
    operator fun invoke(): Flow<Boolean>{
        return localUserManger.readOnBoardingStatus()
    }
}
