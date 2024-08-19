package com.test.authorization.presentation.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.test.authorization.presentation.viewmodel.SignInUiState
import com.test.ui.design.theme.*
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.CountryCode
import com.test.ui.widgets.DefaultButton
import com.test.ui.widgets.LogoTitle
import com.test.ui.widgets.PhoneField
import com.test.ui.widgets.StatusBarInsetsSpacer
import com.test.ui.widgets.SweetBite
import com.test.ui.widgets.TextWithSingleLink

@Composable
fun SendCodeScreen(
    uiState: SignInUiState,
    onCodeChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    onResetClick: () -> Unit
) {
    BackgroundColumn {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        SweetBite(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )
        LogoTitle()
        Spacer(modifier = Modifier.height(space32))

        Text(
            text = stringResource(com.test.chatapp.core.ui.R.string.login),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))

        Row {

            CountryCode{}
            Spacer(modifier = Modifier.width(space8))

            PhoneField(
                modifier = Modifier.fillMaxWidth(),
                value = uiState.phone,
                placeholderResId = com.test.chatapp.core.ui.R.string.phone_placeholder,
                leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_phone,
                hasError = false,
                onValueChange = onPhoneChange,
            )
        }

        Spacer(modifier = Modifier.height(space24))

        Spacer(modifier = Modifier.height(space32))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = com.test.chatapp.core.ui.R.string.login,
            onClick = onLoginClick
        )
        Spacer(modifier = Modifier.height(space24))

        TextWithSingleLink(
            sentence = stringResource(id = com.test.chatapp.core.ui.R.string.not_a_member_register),
            onLinkClicked = onRegisterClick
        )
        Spacer(modifier = Modifier.height(space16))

        TextWithSingleLink(
            sentence = stringResource(com.test.chatapp.core.ui.R.string.forgot_password_reset),
            onLinkClicked = onResetClick
        )
    }
}


@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun SendCodeScreenPreview() {
    ChatAppTheme {
        SendCodeScreen(uiState = SignInUiState(
            code = "+7",
            phone = ""),
            onLoginClick = {}, onRegisterClick = {}, onResetClick = {},
            onCodeChange = {},
            onPhoneChange = {})
    }
}