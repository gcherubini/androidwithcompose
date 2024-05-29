package com.cherubini.news.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cherubini.news.presentation.detail.DetailScreenComposable
import com.cherubini.news.presentation.home.HomeScreenComposable
import com.cherubini.news.presentation.home.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = HomeScreenRoute) {
        composable<HomeScreenRoute> {
//             val viewModel = hiltViewModel<HomeViewModel>()
            val viewModel = viewModel<HomeViewModel>()

            val nextRouteStateFlow by viewModel.nextRouteStateFlow.collectAsState()

            LaunchedEffect(key1 = nextRouteStateFlow) {
                nextRouteStateFlow?.let {
                    navController.navigate(it)
                }
            }

            HomeScreenComposable(viewModel)
        }
        composable<DetailScreenRoute> {
            val args = it.toRoute<DetailScreenRoute>()
            DetailScreenComposable(name = args.userName)
        }
    }
}
