package com.cherubini.news.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.cherubini.news.domain.usecases.StoreUserLoggedInUseCase
import com.cherubini.news.presentation.navgraph.HomeRoute
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
    val storeUserLoggedInUseCase: StoreUserLoggedInUseCase
): ViewModel() {
    // flow state
    private val _inputtedUserName = MutableStateFlow(EMPTY_TEXT)
    val inputtedUserName = _inputtedUserName.asStateFlow()

    // compose state
    var inputtedPassword by mutableStateOf(EMPTY_TEXT)
        private set

    var errorMessage by mutableStateOf(EMPTY_TEXT)
        private set

    var isAdvanceButtonEnabled by mutableStateOf(false)
        private set

    private var _nextRouteStateFlow: MutableStateFlow<HomeRoute?> = MutableStateFlow(null)
    var nextRouteStateFlow : StateFlow<HomeRoute?> = _nextRouteStateFlow.asStateFlow()

    fun onInputUserName(inputtedUserName: String) {
        this._inputtedUserName.value = inputtedUserName
        toggleSignInButton()
    }

    fun onInputPassword(inputtedPassword: String) {
        this.inputtedPassword = inputtedPassword
        toggleSignInButton()
    }

    private fun toggleSignInButton() {
        if(inputtedUserName.value.isBlank() || inputtedPassword.isBlank()) {
            isAdvanceButtonEnabled = false
        } else {
            isAdvanceButtonEnabled = true
        }
    }

    fun onSubmitButtonClick() {
        if(validateFields()) {
            viewModelScope.launch {
                storeUserLoggedInUseCase(inputtedUserName.value)
            }
            _nextRouteStateFlow.value = HomeRoute(inputtedUserName.value)
        }
    }

    private fun validateFields(): Boolean {
        return if(inputtedUserName.value.isBlank() || inputtedPassword.isBlank()) {
            errorMessage = DEFAULT_ERROR_MESSAGE
            false
        } else {
            errorMessage = EMPTY_TEXT
            true
        }
    }
}