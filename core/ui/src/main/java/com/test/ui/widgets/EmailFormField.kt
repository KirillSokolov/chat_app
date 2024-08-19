package com.test.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.test.chatapp.core.ui.R
import com.test.ui.widgets.FormField

@Composable
fun EmailFormField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange:(String)->Unit
) {
    FormField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        placeholderResId = R.string.email_placeholder,
        leadingIconResId = R.drawable.ic_mail
    )
}