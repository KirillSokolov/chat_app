package com.test.ui.widgets

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun ScreenTitle(modifier: Modifier = Modifier, @StringRes textResId: Int) {
    Text (modifier = modifier,
        text = stringResource(id = textResId),
        style = MaterialTheme.typography.headlineLarge,
        color = MaterialTheme.colorScheme.primary)
}
