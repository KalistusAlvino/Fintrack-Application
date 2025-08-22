package com.example.fintrack.presentation.success

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.resendVerify.ResendVerifyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SuccessViewModel @Inject constructor(
    private val resendVerifyUseCase: ResendVerifyUseCase
) : ViewModel() {
    private val _resendVerifyState = mutableStateOf(SuccessState())
    val resendVerifyState: State<SuccessState> get() = _resendVerifyState

    private val _countdown = mutableIntStateOf(0)
    val countdown: State<Int> get() = _countdown

    private val _isRunning = mutableStateOf(false)
    val isRunning: State<Boolean> get() = _isRunning

    private var countdownJob: Job? = null


    fun onEvent(event: SuccessEvent) {
        when (event) {
            is SuccessEvent.resendVerify -> {
                resendVerify(event.email)
                startCountdown(5 * 60) // 5 menit = 300 detik
            }
        }
    }

    fun resendVerify(
        email: String
    ) {
        resendVerifyUseCase.invoke(email).onEach {
            when (it) {
                is ResultState.Loading -> {
                    _resendVerifyState.value = SuccessState(isLoading = true)
                    Log.d("Register View Model", _resendVerifyState.value.isLoading.toString())
                }

                is ResultState.Success -> {
                    _resendVerifyState.value =
                        SuccessState(
                            data = it.data,
                            message = it.message.toString(),
                            success = true
                        )
                    Log.d(
                        "Register View Model Success",
                        _resendVerifyState.value.message.toString()
                    )
                }

                is ResultState.Error -> {
                    _resendVerifyState.value = SuccessState(message = it.message.toString())
                    Log.d("Register View Model Error", _resendVerifyState.value.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun startCountdown(seconds: Int) {
        countdownJob?.cancel()
        countdownJob =  viewModelScope.launch {
            _isRunning.value =  true
            _countdown.value = seconds
            while (_countdown.value > 0) {
                delay(1000L)
                _countdown.value -= 1
            }
        }
    }
}
