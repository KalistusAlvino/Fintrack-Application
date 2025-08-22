package com.example.fintrack.presentation.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.domain.usecase.appentry.AppEntryUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnBoardingViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases
): ViewModel(){

    fun onEvent(event: OnBoardingEvent) {
        when(event) {
            is OnBoardingEvent.saveOnBoardingStatus -> {
                saveOnBoardingStatus()
            }
        }
    }

    private fun saveOnBoardingStatus(){
        viewModelScope.launch {
            appEntryUseCases.saveOnBoardingStatus()
        }
    }
}