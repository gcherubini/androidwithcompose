package com.cherubini.news.di

import android.app.Application
import com.cherubini.news.data.manager.LocalUserManagerImplementation
import com.cherubini.news.domain.manager.LocalUserManager
import com.cherubini.news.domain.usecases.CheckUserLoggedInUseCase
import com.cherubini.news.domain.usecases.LoginUseCase
import com.cherubini.news.presentation.detail.HomeViewModel
import com.cherubini.news.presentation.home.LoginViewModel
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
    fun provideLoginUseCase(localUserManager: LocalUserManager): LoginUseCase =
        LoginUseCase(localUserManager)

    @Provides
    fun provideCheckLoggedInUseCase(localUserManager: LocalUserManager): CheckUserLoggedInUseCase =
        CheckUserLoggedInUseCase(localUserManager)

    @Provides
    fun provideLoginViewModel(loginUseCase: LoginUseCase): LoginViewModel =
        LoginViewModel(loginUseCase = loginUseCase)

    @Provides
    fun provideHomeViewModel(checkLoggedInUseCase: CheckUserLoggedInUseCase): HomeViewModel =
        HomeViewModel(checkUserLoggedInUseCase = checkLoggedInUseCase)
}