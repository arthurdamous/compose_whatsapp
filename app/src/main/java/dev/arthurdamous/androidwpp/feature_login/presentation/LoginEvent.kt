package dev.arthurdamous.androidwpp.feature_login.presentation

sealed class LoginEvent {
    object OnLogin : LoginEvent()
    object OnRegister : LoginEvent()
    data class OnChangeEmail(val text: String) : LoginEvent()
    data class OnChangePassword(val text: String) : LoginEvent()
}