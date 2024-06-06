package com.cherubini.news.presentation.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherubini.news.data.network.NewResponse
import com.cherubini.news.domain.usecases.FetchNewsUseCase
import com.cherubini.news.domain.usecases.GetUserLoggedInUseCase
import com.cherubini.news.domain.usecases.StoreUserLoggedInUseCase
import com.cherubini.news.presentation.navgraph.LoginRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val getUserLoggedInUseCase: GetUserLoggedInUseCase,
    val fetchNewsUseCase: FetchNewsUseCase,
    val storeUserLoggedInUseCase: StoreUserLoggedInUseCase
): ViewModel() {
    var storedUserLoggedIn by mutableStateOf<String?>(null)
        private set

    var news by mutableStateOf<List<NewResponse>?>(null)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    val animatedLoadingMessage = flow {
        var counter = 0
        while (true) {
            counter++

            var ellipsis = ""
            for (i in 1..counter) {
                ellipsis += "."

            }
            emit("Loading$ellipsis")

            if(counter == 3) {
                counter = 0
            }

            delay(1000)
        }
    }

    private var _logoutStateFlow: MutableStateFlow<LoginRoute?> = MutableStateFlow(null)
    var logoutStateFlow : StateFlow<LoginRoute?> = _logoutStateFlow.asStateFlow()

    init {
        checkIfUserIsLoggedIn()
        fetchNews()
    }

   private fun checkIfUserIsLoggedIn() {
       viewModelScope.launch {
           getUserLoggedInUseCase.invoke().collect{
               storedUserLoggedIn = it
           }
       }
   }

    private fun fetchNews() {
        isLoading = true
        viewModelScope.launch {
            try {
                news = fetchNewsUseCase().body()
            } catch (ex: Exception) {
                errorMessage = ex.message
            }

            isLoading = false
        }
    }

    fun onLogoutClick() {
        _logoutStateFlow.value = LoginRoute

        viewModelScope.launch {
            storeUserLoggedInUseCase(null)
        }
    }
}