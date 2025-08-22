package com.example.fintrack.domain.usecase.appentry

import kotlinx.coroutines.flow.Flow

data class AppEntryUseCases(
    val saveOnBoardingStatus: SaveOnBoardingStatus,
    val readOnBoardingStatus: ReadOnBoardingStatus
)
