package com.cherubini.news.di

import android.app.Application
import com.cherubini.news.data.manager.LocalUserManagerImplementation
import com.cherubini.news.data.network.NetworkModule
import com.cherubini.news.data.network.NetworkService
import com.cherubini.news.domain.manager.LocalUserManager
import com.cherubini.news.domain.usecases.FetchNewsUseCase
import com.cherubini.news.domain.usecases.GetUserLoggedInUseCase
import com.cherubini.news.domain.usecases.StoreUserLoggedInUseCase
import com.cherubini.news.presentation.home.HomeViewModel
import com.cherubini.news.presentation.login.LoginViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    // Data
    @Provides
    @Singleton
    fun provideLocalUserManager(application: Application): LocalUserManager =
        LocalUserManagerImplementation(application)

    @Provides
    @Singleton
    fun provideNetworkModule(): NetworkModule =
        NetworkModule()

    @Provides
    @Singleton
    fun provideNetworkService(networkModule: NetworkModule): NetworkService =
        NetworkService(networkModule.init())

    // Use Cases

    @Provides
    fun provideLoginUseCase(localUserManager: LocalUserManager): StoreUserLoggedInUseCase =
        StoreUserLoggedInUseCase(localUserManager)

    @Provides
    fun provideCheckLoggedInUseCase(localUserManager: LocalUserManager): GetUserLoggedInUseCase =
        GetUserLoggedInUseCase(localUserManager)

    @Provides
    fun provideFetchNewsUseCase(networkService: NetworkService): FetchNewsUseCase =
        FetchNewsUseCase(networkService)

    // View Models

    @Provides
    fun provideLoginViewModel(loginUseCase: StoreUserLoggedInUseCase): LoginViewModel =
        LoginViewModel(storeUserLoggedInUseCase = loginUseCase)

    @Provides
    fun provideHomeViewModel(checkLoggedInUseCase: GetUserLoggedInUseCase, fetchNewsUseCase: FetchNewsUseCase, storeUserLoggedInUseCase: StoreUserLoggedInUseCase): HomeViewModel =
        HomeViewModel(
            getUserLoggedInUseCase = checkLoggedInUseCase,
            fetchNewsUseCase = fetchNewsUseCase,
            storeUserLoggedInUseCase = storeUserLoggedInUseCase
        )
}