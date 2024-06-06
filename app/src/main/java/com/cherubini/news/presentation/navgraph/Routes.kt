package com.cherubini.news.presentation.navgraph

import kotlinx.serialization.Serializable

interface Route

@Serializable
object LoginRoute: Route

@Serializable
data class HomeRoute(
    val userName: String
): Route
