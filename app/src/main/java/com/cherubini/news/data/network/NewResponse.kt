package com.cherubini.news.data.network

import com.squareup.moshi.Json

data class NewResponse(
    @Json(name = "imageSrc") val imageSrc: String,
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String
)
