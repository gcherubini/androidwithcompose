package com.cherubini.news.domain.usecases

import com.cherubini.news.data.network.NetworkService
import com.cherubini.news.data.network.NewResponse
import retrofit2.Response
import java.lang.IllegalStateException

private const val GENERIC_ERROR = "Something went wrong, try again later.."
class FetchNewsUseCase(private val networkService: NetworkService) {
    suspend operator fun invoke(): Response<List<NewResponse>> {
            try {
                val response = networkService.getNews()
                if (response?.isSuccessful == true) {
                   return response
                } else {
                    throw IllegalStateException(GENERIC_ERROR)
                    //Handle unsuccessful response
                }
            } catch (e: Exception) {
                throw IllegalStateException(GENERIC_ERROR)
                //Handle error
            }
    }
}