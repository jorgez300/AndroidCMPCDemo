package com.demo.demobaseandroid2.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.demo.demobaseandroid2.data.database.AppDatabase
import com.demo.demobaseandroid2.data.entity.User
import com.demo.demobaseandroid2.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class LoginState(
    val email: String? = null,
    val password: String? = null,
    val vip: Boolean = false,
    val isLoggedIn: Boolean = false, // Nuevo estado para indicar si el login fue exitoso
    val error: String? = null // Nuevo estado para manejar errores
)

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    // Inicializar la base de datos y el repositorio
    private val database = Room.databaseBuilder(
        application,
        AppDatabase::class.java, "app_database"
    ).build()
    private val userRepository = UserRepository(database.userDao())

    // Manejo del estado de la UI
    private val _uiState = MutableStateFlow(LoginState())
    val uiState: StateFlow<LoginState> = _uiState.asStateFlow()

    fun updateState(
        email: String? = _uiState.value.email,
        password: String? = _uiState.value.password,
        vip: Boolean = _uiState.value.vip,
        isLoggedIn: Boolean = _uiState.value.isLoggedIn,
        error: String? = _uiState.value.error
    ) {
        _uiState.update { currentState ->
            currentState.copy(
                email = email,
                password = password,
                vip = vip,
                isLoggedIn = isLoggedIn,
                error = error
            )
        }
    }

    fun login(username: String, password: String) {
        viewModelScope.launch {
            try {
                val user = userRepository.getUserByUsernameAndPassword(username, password)
                if (user != null) {
                    // Usuario encontrado, login exitoso
                    updateState(
                        email = user.username,
                        password = user.password,
                        isLoggedIn = true,
                        error = null
                    )
                } else {
                    // Usuario no encontrado, crear uno nuevo
                    val newUser = User(username = username, password = password)
                    userRepository.insertUser(newUser)
                    updateState(
                        email = newUser.username,
                        password = newUser.password,
                        isLoggedIn = true,
                        error = null
                    )
                }
            } catch (e: Exception) {
                // Manejar errores de la base de datos
                updateState(error = "Error de base de datos: ${e.message}")
            }
        }
    }
}