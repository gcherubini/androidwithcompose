package com.cherubini.news.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes

@Composable
fun HomeComposable(name: String, viewModel: HomeViewModel) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Text(text = "Hello, $name")
            Text(text = "Data Store, isLoggedIn, ${viewModel.isUserLoggedInValue}")
        }
    }
}

//@PreviewScreenSizes
//@Composable
//fun PreviewDifferentScreenSizes() {
//    HomeComposable(name = "Guilherme")
//}
