package com.example.fintrack.presentation.register

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.auth.RegisterUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel  @Inject constructor(
    private val registerUseCase: RegisterUseCase
): ViewModel(){
    private val _registerState = mutableStateOf(RegisterState())
    val registerState: State<RegisterState> get() = _registerState

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is  RegisterEvent.Register-> {
                register(event.username, event.email, event.password, event.confirmPassword)
            }
        }
    }

    fun register(
        username: String,
        email: String,
        password: String,
        confirmPassword: String
    ) {
        registerUseCase.invoke(username, email, password, confirmPassword).onEach {
            when(it) {
                is ResultState.Loading -> {
                    _registerState.value = RegisterState(isLoading = true)
                    Log.d("Register View Model", _registerState.value.isLoading.toString())
                }
                is ResultState.Success -> {
                    _registerState.value = RegisterState(data = it.data, message = it.message.toString(), success = true)
                    Log.d("Register View Model Success", _registerState.value.message.toString())
                }
                is ResultState.Error -> {
                    _registerState.value = RegisterState(message = it.message.toString())
                    Log.d("Register View Model Error", _registerState.value.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}