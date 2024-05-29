package com.cherubini.news.domain.usecases

import com.cherubini.news.domain.manager.LocalUserManager

class SaveAppEntryUseCase(private val localUserManager: LocalUserManager) {
    suspend operator fun invoke() {
        localUserManager.saveAppEntry()
    }
}