package com.cherubini.news.presentation.navgraph

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.cherubini.news.presentation.home.HomeComposable
import com.cherubini.news.presentation.home.HomeViewModel
import com.cherubini.news.presentation.login.LoginComposable
import com.cherubini.news.presentation.login.LoginViewModel

@Composable
fun NavGraph(userLoggedIn: String?) {
    val navController = rememberNavController()

    val startDestination = if(userLoggedIn.isNullOrEmpty()) {
        LoginRoute
    } else {
        HomeRoute(userLoggedIn)
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable<LoginRoute> {
            val viewModel = hiltViewModel<LoginViewModel>()

            val nextRouteStateFlow by viewModel.nextRouteStateFlow.collectAsState()
            LaunchedEffectNavigation(nextRouteStateFlow, navController)

            LoginComposable(viewModel)
        }
        composable<HomeRoute> {
            val args = it.toRoute<HomeRoute>()

            val viewModel = hiltViewModel<HomeViewModel>()
            val logoutStateFlow by viewModel.logoutStateFlow.collectAsState()
            LaunchedEffectNavigation(logoutStateFlow, navController)

            HomeComposable(
                userNameArg = args.userName,
                storedUserLoggedIn = viewModel.storedUserLoggedIn,
                news = viewModel.news,
                isLoading = viewModel.isLoading,
                errorMessage = viewModel.errorMessage,
                onLogoutButtonClick = {
                    viewModel.onLogoutClick()
                },
                loadingMessage = viewModel.animatedLoadingMessage.collectAsState("Loading").value
            )
        }
    }
}

@Composable
private fun LaunchedEffectNavigation(
    nextRouteStateFlow: Route?,
    navController: NavHostController
) {
    LaunchedEffect(key1 = nextRouteStateFlow) {
        nextRouteStateFlow?.let { nextRoute ->
            navController.navigate(nextRoute)
        }
    }
}
