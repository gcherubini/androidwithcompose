package com.cherubini.news.domain.usecases

import com.cherubini.news.domain.manager.LocalUserManager
import kotlinx.coroutines.flow.Flow

class GetUserLoggedInUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke(): Flow<String?> {
        return localUserManager.getUserLoggedIn()
    }
}