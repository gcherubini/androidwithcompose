package com.cherubini.news.presentation.login
data class LoginState(
    var inputtedUserName: String = "",
    var inputtedPassword: String = "",
    var errorMessage: String = "",
    var isSubmitButtonEnabled: Boolean = false
)