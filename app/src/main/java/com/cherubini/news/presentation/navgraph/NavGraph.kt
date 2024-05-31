package com.cherubini.news.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cherubini.news.presentation.detail.HomeComposable
import com.cherubini.news.presentation.detail.HomeViewModel
import com.cherubini.news.presentation.home.LoginComposable
import com.cherubini.news.presentation.home.LoginViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> {
            val viewModel = hiltViewModel<LoginViewModel>()

            val nextRouteStateFlow by viewModel.nextRouteStateFlow.collectAsState()

            LaunchedEffect(key1 = nextRouteStateFlow) {
                nextRouteStateFlow?.let {
                    navController.navigate(it)
                }
            }

            LoginComposable(viewModel)
        }
        composable<DetailScreenRoute> {
            val args = it.toRoute<DetailScreenRoute>()

            val viewModel = hiltViewModel<HomeViewModel>()
            HomeComposable(name = args.userName, viewModel)
        }
    }
}
