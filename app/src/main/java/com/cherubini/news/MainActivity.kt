package com.cherubini.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.cherubini.news.domain.usecases.GetUserLoggedInUseCase
import com.cherubini.news.presentation.navgraph.NavGraph
import com.cherubini.news.ui.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var getUserLoggedInUseCase: GetUserLoggedInUseCase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Show splash screen
        installSplashScreen()

        // Get user logged in and Start main compose content
        getUserLoggedIn()
    }

    private fun getUserLoggedIn() {
        lifecycleScope.launch {
            getUserLoggedInUseCase.invoke().collect() {
                setMainComposeContent(it)
            }
        }
    }

    private fun setMainComposeContent(userIsLoggedIn: String?) {
        // Jetpack compose UI starts
        setContent {
            NewsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Application Navigation and NavGraph
                    NavGraph(userIsLoggedIn)
                }
            }
        }
    }
}