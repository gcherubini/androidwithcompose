package com.cherubini.news.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.cherubini.news.domain.usecases.LoginUseCase
import com.cherubini.news.presentation.navgraph.DetailScreenRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val EMPTY_TEXT = ""
private const val DEFAULT_ERROR_MESSAGE = "Fill the username and password fields!"

@HiltViewModel
class LoginViewModel @Inject constructor(
    val loginUseCase: LoginUseCase
): ViewModel() {
    var inputtedUserName by mutableStateOf(EMPTY_TEXT)
        private set
    var inputtedPassword by mutableStateOf(EMPTY_TEXT)
        private set

    var errorMessage by mutableStateOf(EMPTY_TEXT)
        private set

    var isAdvanceButtonEnabled by mutableStateOf(false)
        private set

    private var _nextRouteStateFlow: MutableStateFlow<DetailScreenRoute?> = MutableStateFlow(null)
    var nextRouteStateFlow : StateFlow<DetailScreenRoute?> = _nextRouteStateFlow.asStateFlow()

    fun onInputUserName(inputtedUserName: String) {
        this.inputtedUserName = inputtedUserName
        toggleSignInButton()
    }

    fun onInputPassword(inputtedPassword: String) {
        this.inputtedPassword = inputtedPassword
        toggleSignInButton()
    }

    private fun toggleSignInButton() {
        if(inputtedUserName.isBlank() || inputtedPassword.isBlank()) {
            isAdvanceButtonEnabled = false
        } else {
            isAdvanceButtonEnabled = true
        }
    }

    fun onAdvanceButtonClick() {
        if(validateFields()) {
            viewModelScope.launch {
                loginUseCase()
            }
            _nextRouteStateFlow.value = DetailScreenRoute(inputtedUserName)
        }
    }

    private fun validateFields(): Boolean {
        return if(inputtedUserName.isBlank() || inputtedPassword.isBlank()) {
            errorMessage = DEFAULT_ERROR_MESSAGE
            false
        } else {
            errorMessage = EMPTY_TEXT
            true
        }
    }
}