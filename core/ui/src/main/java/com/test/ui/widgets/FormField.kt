package com.test.ui.widgets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.*


@Composable
fun FormField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes placeholderResId: Int,
    @DrawableRes leadingIconResId: Int,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    hasError: Boolean = false
) {
    TextField(
        value = value,
        shape = RoundedCornerShape(8.dp),
        modifier =
        modifier
            .height(56.dp),
        placeholder = {
            Text(
                text = stringResource(id = placeholderResId),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        visualTransformation = visualTransformation,
        onValueChange = onValueChange,
        leadingIcon = {
            Image(
                modifier = Modifier
                    .height(itemHeight24)
                    .width(itemWidth24),
                painter = painterResource(id = leadingIconResId),
                contentDescription = "form icon",
                contentScale = ContentScale.None
            )

        },
        trailingIcon = {
            if(hasError){
                Image(
                    modifier = Modifier
                        .height(itemHeight24)
                        .width(itemWidth24),
                    painter = painterResource(id = R.drawable.ic_error),
                    contentDescription = "form error icon",
                    contentScale = ContentScale.None
                )
            }
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background
        )
    )
}


@Preview(showBackground = true)
@Composable
fun FormFieldPreview() {
    ChatAppTheme {
        FormField(
            value = "test@test.ru",
            placeholderResId = R.string.email_placeholder,
            leadingIconResId = R.drawable.ic_mail,
            onValueChange = {},
            hasError = true
        )
    }
}