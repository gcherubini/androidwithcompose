package com.cherubini.news.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun storeUserLoggedIn(userName: String?)
    fun getUserLoggedIn(): Flow<String?>
}