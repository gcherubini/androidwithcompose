package com.cherubini.news.presentation.login

sealed class LoginEvent {
    data class InputUsername(val username: String): LoginEvent()
    data class InputPassword(val password: String): LoginEvent()
    data object Submit: LoginEvent()
}