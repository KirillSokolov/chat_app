package com.test.ui.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.test.chatapp.core.ui.R

@Composable
fun PriceText(value: String) {
    Text(text = stringResource(id = R.string.usd, value),
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary)
}