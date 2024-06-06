package com.cherubini.news.data.network

import retrofit2.Retrofit

class NetworkService(retrofit: Retrofit) {
    private val service = retrofit.create(NetworkInterface::class.java)

    suspend fun getNews() =
        service.getNews()
}