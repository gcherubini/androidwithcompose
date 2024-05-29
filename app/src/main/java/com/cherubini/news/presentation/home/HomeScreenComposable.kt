package com.cherubini.news.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.cherubini.news.presentation.Dimens

@Composable
fun HomeScreenComposable(viewModel: HomeViewModel) {
    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(Dimens.MediumPadding)
    ) {
        Text(text = "Digite seu nome:")
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        TextField(
            value = viewModel.inputtedUserName,
            onValueChange = {
                viewModel.onInputUserName(it)
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        Button(
            onClick = {
                viewModel.onAdvanceButtonClick()
            },
            enabled = viewModel.isAdvanceButtonEnabled,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "To Detail Screen")
        }
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        Text(text = viewModel.errorMessage, color = Color.Red)
    }
}

//@PreviewScreenSizes
//@Composable
//fun PreviewDifferentScreenSizes() {
//    HomeScreenComposable() {}
//}
//
//
//@PreviewFontScale
//@Composable
//fun PreviewFontScale() {
//    HomeScreenComposable() {}
//}
//
//@PreviewLightDark
//@Composable
//fun PreviewLightAndDarkMode() {
//    HomeScreenComposable() {}
//}
//
//@Preview(
//    showSystemUi = true,
//    device = Devices.WEAR_OS_RECT,
//    locale = "en"
//)
//@Composable
//fun PreviewWatch() {
//    HomeScreenComposable() {}
//}