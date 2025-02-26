package com.demo.demobaseandroid2.screens.login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class LoginState(
    val email: String? = "email",
    val password: String? = "password",
    val vip: Boolean = true,
)

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginState>(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun updateState(
        email: String? = _uiState.value.email,
        password: String? = _uiState.value.password,
        vip: Boolean = _uiState.value.vip
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                password = password,
                vip = vip,
            )
        }
    }
}
