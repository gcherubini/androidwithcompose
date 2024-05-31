package com.cherubini.news.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.cherubini.news.R
import com.cherubini.news.presentation.Dimens

@Composable
fun LoginComposable(viewModel: LoginViewModel) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(Dimens.MediumPadding)
    ) {
        // logo image
        Image(
            painter = painterResource(id = R.drawable.news_logo),
            contentDescription = stringResource(id = R.string.login_logo_content_description),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))

        // input username textfield
        Text(text = stringResource(id = R.string.login_sign_in_username))
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        TextField(
            value = viewModel.inputtedUserName,
            onValueChange = {
                viewModel.onInputUserName(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))
        Text(text = stringResource(id = R.string.login_sign_in_password))
        Spacer(modifier = Modifier.height(Dimens.SmallPadding))

        // input password textfield
        TextField(
            value = viewModel.inputtedPassword,
            onValueChange = {
                viewModel.onInputPassword(it)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(Dimens.MediumPadding))

        // sign in button
        Button(
            onClick = {
                viewModel.onAdvanceButtonClick()
            },
            enabled = viewModel.isAdvanceButtonEnabled,
            modifier = Modifier.align(Alignment.CenterHorizontally)

        ) {
            Text(text = stringResource(id = R.string.login_sign_in_button))
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