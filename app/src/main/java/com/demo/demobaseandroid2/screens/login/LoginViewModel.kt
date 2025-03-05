package com.demo.demobaseandroid2.screens.login

import androidx.lifecycle.ViewModel
import com.demo.demobaseandroid2.domain.model.LoginModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<LoginModel>(LoginModel())
    val uiState: StateFlow<LoginModel> = _uiState.asStateFlow()

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
