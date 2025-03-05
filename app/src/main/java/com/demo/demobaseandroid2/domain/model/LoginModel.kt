package com.demo.demobaseandroid2.domain.model

data class LoginModel(
    val email: String? = "email",
    val password: String? = "password",
    val vip: Boolean = true,
)