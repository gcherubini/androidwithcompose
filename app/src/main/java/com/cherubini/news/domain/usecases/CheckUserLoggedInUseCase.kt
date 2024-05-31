package com.cherubini.news.domain.usecases

import com.cherubini.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class CheckUserLoggedInUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(): Flow<Boolean> {
        return localUserManager.isLoggedIn()
    }
}