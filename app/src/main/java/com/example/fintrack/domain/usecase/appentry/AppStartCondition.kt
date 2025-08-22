package com.example.fintrack.domain.usecase.appentry

data class AppStartCondition(
    val isOnBoardingDone: Boolean,
    val isLoggedIn: Boolean,
)
