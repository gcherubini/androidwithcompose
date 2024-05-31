package com.cherubini.news.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cherubini.news.domain.usecases.CheckUserLoggedInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val checkUserLoggedInUseCase: CheckUserLoggedInUseCase
): ViewModel() {
    var isUserLoggedInValue by mutableStateOf<Boolean?>(null)
        private set

    init {
        checkIfUserIsLoggedIn()
    }

   private fun checkIfUserIsLoggedIn() {
       viewModelScope.launch {
           checkUserLoggedInUseCase.invoke().collect{
               isUserLoggedInValue = it
           }
       }
   }
}