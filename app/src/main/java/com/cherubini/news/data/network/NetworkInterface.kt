package com.cherubini.news.data.network

import retrofit2.Response
import retrofit2.http.GET

interface NetworkInterface {
    @GET("cherubini/news")
    suspend fun getNews(): Response<List<NewResponse>>?
}