package com.cherubini.news.di

import android.app.Application
import com.cherubini.news.data.manager.LocalUserManagerImplementation
import com.cherubini.news.domain.manager.LocalUserManager
import com.cherubini.news.domain.usecases.AppEntryUseCases
import com.cherubini.news.domain.usecases.ReadAppEntryUseCase
import com.cherubini.news.domain.usecases.SaveAppEntryUseCase
import com.cherubini.news.presentation.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntryUseCase(localUserManager),
            saveAppEntry = SaveAppEntryUseCase(localUserManager)
        )

    @Provides
    fun provideHomeViewModel(): HomeViewModel =
        HomeViewModel()
}