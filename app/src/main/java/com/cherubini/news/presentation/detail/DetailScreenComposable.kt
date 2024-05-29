package com.cherubini.news.presentation.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import com.cherubini.news.presentation.home.HomeScreenComposable

@Composable
fun DetailScreenComposable(name: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello, $name")
    }
}

@PreviewScreenSizes
@Composable
fun PreviewDifferentScreenSizes() {
    DetailScreenComposable(name = "Guilherme")
}
