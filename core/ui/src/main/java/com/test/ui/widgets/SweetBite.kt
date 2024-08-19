package com.test.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.ChatAppTheme

@Composable
fun SweetBite(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.sweet_bite),
            contentDescription = stringResource(R.string.sweet_bite),
            contentScale = ContentScale.Fit
        )
    }
}

@Preview
@Composable
fun SweetBitePreview() {
    ChatAppTheme {
        SweetBite()
    }
}