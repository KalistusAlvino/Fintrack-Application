package com.example.fintrack.presentation.mainactivity

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.data.pref.UserPreference
import com.example.fintrack.domain.usecase.appentry.AppEntryUseCases
import com.example.fintrack.domain.usecase.appentry.AppStartCondition
import com.example.fintrack.presentation.navgraph.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val appEntryUseCases: AppEntryUseCases,
    private val userPreference: UserPreference
) : ViewModel() {

    private val _splashCondition = mutableStateOf(true)
    val splashCondition: State<Boolean> = _splashCondition

    private val _startDestination = mutableStateOf(Route.AppStartNavigation.route)
    val startDestination: State<String> = _startDestination

    init {
        combine(
            appEntryUseCases.readOnBoardingStatus(),
            userPreference.readLoginStatus(),
        ) { onBoardingStatus, loginStatus->
            AppStartCondition(
                onBoardingStatus,
                loginStatus
            )
        }.onEach { condition ->
            _startDestination.value = when {
                !condition.isOnBoardingDone -> Route.AppStartNavigation.route
                !condition.isLoggedIn -> Route.AppAuthNavigation.route
                else -> Route.FintrackNavigation.route
            }
            delay(200) //Without this delay, the onBoarding screen will show for a momentum.
            _splashCondition.value = false
        }.launchIn(viewModelScope)
    }
}
