package com.cherubini.news.presentation.navgraph

import kotlinx.serialization.Serializable

@Serializable
object HomeScreenRoute

@Serializable
data class DetailScreenRoute(
    val userName: String
)
