package com.cherubini.news.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalUserManager {
    suspend fun login()
    fun isLoggedIn(): Flow<Boolean>
}