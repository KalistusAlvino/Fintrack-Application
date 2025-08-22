package com.example.fintrack.domain.usecase.appentry

import com.example.fintrack.domain.manger.LocalUserManger

class SaveOnBoardingStatus(
    private val localUserManger: LocalUserManger
) {
    suspend operator fun invoke(){
        return localUserManger.saveOnBoardingStatus()
    }
}