package com.cherubini.news.domain.usecases

import com.cherubini.news.domain.manager.LocalUserManager

class StoreUserLoggedInUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(userName: String?) {
        localUserManager.storeUserLoggedIn(userName)
    }
}