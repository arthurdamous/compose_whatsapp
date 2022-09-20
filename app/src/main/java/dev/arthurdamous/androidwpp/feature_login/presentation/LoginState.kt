package dev.arthurdamous.androidwpp.feature_login.presentation

data class LoginState(
    val isLoading: Boolean = false,
    val email: String = "",
    val password: String = ""
)