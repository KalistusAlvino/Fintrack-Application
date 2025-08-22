package com.example.fintrack.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fintrack.core.common.ResultState
import com.example.fintrack.domain.usecase.login.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
) : ViewModel() {
    private val _loginState = mutableStateOf(LoginState())
    val loginState: State<LoginState> get() = _loginState

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.Login -> {
                login(event.username,event.password)
            }
        }
    }

    fun login(
        username: String,
        password: String,
    ) {
        loginUseCase.invoke(username, password).onEach {
            when (it) {
                is ResultState.Loading -> {
                    _loginState.value = LoginState(isLoading = true)
                }
                is ResultState.Success -> {
                    _loginState.value =
                        LoginState(data = it.data, message = it.message.toString(), success = true)
                }
                is ResultState.Error -> {
                    _loginState.value = LoginState(message = it.message.toString())
                }
            }
        }.launchIn(viewModelScope)
    }
}