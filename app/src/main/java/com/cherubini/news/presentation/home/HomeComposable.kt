package com.cherubini.news.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cherubini.news.data.network.NewResponse

@Composable
fun HomeComposable(
    userNameArg: String,
    storedUserLoggedIn: String?,
    news: List<NewResponse>?,
    errorMessage: String?,
    isLoading: Boolean?,
    onLogoutButtonClick: () -> Unit,
    loadingMessage: State<String>,

    ) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Hello, $storedUserLoggedIn",
                    textAlign = TextAlign.Start,
                )
                Text(
                    text = "Logout",
                    textAlign = TextAlign.End,
                    fontSize = 12.sp,
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onLogoutButtonClick() }
                )
            }

            Text(
                text = "The latest news are:",
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 32.dp, bottom = 24.dp)
            )
            if(isLoading == true){
                LoadingItem(loadingMessage)
            }
            news?.forEach {
                NewItem(it)
            }
            errorMessage?.let {
                ErrorItem(it)
            }
        }
    }
}

@Composable
fun NewItem(newResponse: NewResponse) {
    Text(
        text = newResponse.title,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    Text(
        text = newResponse.description,
        textAlign = TextAlign.Start,
        modifier = Modifier.padding(bottom = 12.dp),
        fontSize = 11.sp,
        overflow = TextOverflow.Ellipsis,
        maxLines = 4
    )
}

@Composable
fun LoadingItem(loadingMessage: State<String>) {
    Text(
        text = loadingMessage.value,
        textAlign = TextAlign.Center,
        color = Color.Blue,
        modifier = Modifier
            .fillMaxWidth()
    )
}

@Composable
fun ErrorItem(errorMessage: String) {
    Text(
        text = errorMessage,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
    )
}

//@PreviewScreenSizes
//@Composable
//fun PreviewDifferentScreenSizes() {
//    HomeComposable(
//        userNameArg = "example of argument",
//        storedUserLoggedIn = "Stored user name in Data Store",
//        news = listOf(NewResponse("any", "New mocked title", "any")),
//        errorMessage = null,
//        isLoading = false,
//        onLogoutButtonClick = {},
//        loadingMessage = viewModel.loadingMessage.collectAsState("Loading"),
//    )
//}
