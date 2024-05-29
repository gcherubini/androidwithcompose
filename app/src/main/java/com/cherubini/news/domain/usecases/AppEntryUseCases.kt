package com.cherubini.news.domain.usecases

data class AppEntryUseCases(
    val readAppEntry: ReadAppEntryUseCase,
    val saveAppEntry: SaveAppEntryUseCase
)