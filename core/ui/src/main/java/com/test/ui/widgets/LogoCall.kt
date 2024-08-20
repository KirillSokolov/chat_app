package com.test.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.*

@Composable
fun LogoSlogan(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(text = buildAnnotatedString {
            withStyle(
                style = highlightSpanStyle
            ) {
                append(stringResource(id = R.string.enjoy))
            }
            append("\n")
            withStyle(
                style = defaultCapsSpanStyle
            ) {
                append(stringResource(id = R.string.your_work))
            }
        },
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = false)
@Composable
fun LogoCallPreview() {
    ChatAppTheme {
        LogoSlogan()
    }
}