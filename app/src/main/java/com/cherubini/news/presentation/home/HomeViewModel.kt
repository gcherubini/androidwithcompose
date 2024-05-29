package com.cherubini.news.presentation.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.cherubini.news.presentation.navgraph.DetailScreenRoute
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val EMPTY_TEXT = ""
private const val DEFAULT_ERROR_MESSAGE = "Você precisa preencher o campo de usuário!"

//@HiltViewModel
class HomeViewModel: ViewModel() {
    var inputtedUserName by mutableStateOf(EMPTY_TEXT)
        private set

    var errorMessage by mutableStateOf(EMPTY_TEXT)
        private set

    var isAdvanceButtonEnabled by mutableStateOf(false)
        private set

    private var _nextRouteStateFlow: MutableStateFlow<DetailScreenRoute?> = MutableStateFlow(null)
    var nextRouteStateFlow : StateFlow<DetailScreenRoute?> = _nextRouteStateFlow.asStateFlow()

    fun onInputUserName(inputtedUserName: String) {
        this.inputtedUserName = inputtedUserName

        if(inputtedUserName.isBlank()) {
            errorMessage = DEFAULT_ERROR_MESSAGE
            isAdvanceButtonEnabled = false
        } else {
            errorMessage = EMPTY_TEXT
            isAdvanceButtonEnabled = true
        }
    }

    fun onAdvanceButtonClick() {
        if(inputtedUserName.isNotBlank()) {
            _nextRouteStateFlow.value = DetailScreenRoute(inputtedUserName)
        }
    }
}