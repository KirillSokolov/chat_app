package com.test.authorization.presentation.ui

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
import com.test.authorization.presentation.viewmodel.RegistrationState
import com.test.data.temp.UserData
import com.test.ui.design.theme.*
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.DefaultButton
import com.test.ui.widgets.FormField
import com.test.ui.widgets.LogoTitle
import com.test.ui.widgets.StatusBarInsetsSpacer
import com.test.ui.widgets.IconHeader
import com.test.ui.widgets.TextWithSingleLink

@Composable
fun RegistrationScreen(
    uiState: RegistrationState,
    onNameChange: (String) -> Unit,
    onNicknameChange: (String) -> Unit,
    onRegisterClick: ()->Unit) {
    BackgroundColumn {
        StatusBarInsetsSpacer()
        Spacer(modifier = Modifier.height(space16))
        IconHeader(
            modifier = Modifier
                .height(itemHeight75)
                .width(itemWidth104)
        )
        LogoTitle()
        Spacer(modifier = Modifier.height(space32))

        Text(
            text = stringResource(com.test.chatapp.core.ui.R.string.sign_up),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))
        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.name,
            placeholderResId = com.test.chatapp.core.ui.R.string.user_name,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_person,
            onValueChange = onNameChange
        )
        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = uiState.nickName,
            placeholderResId = com.test.chatapp.core.ui.R.string.user_nickname,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_person,
            onValueChange = onNicknameChange
        )
//        EmailFormField(
//            modifier = Modifier.fillMaxWidth(),
//            value = "",
//            onValueChange = onNicknameChange
//        )
        Spacer(modifier = Modifier.height(space24))

        FormField(
            modifier = Modifier.fillMaxWidth(),
            value = UserData.phone,
            enabled = false,
            placeholderResId = com.test.chatapp.core.ui.R.string.name_placeholder,
            leadingIconResId = com.test.chatapp.core.ui.R.drawable.ic_phone,
            onValueChange = {}
        )
        Spacer(modifier = Modifier.height(space56))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = com.test.chatapp.core.ui.R.string.signup, onClick = onRegisterClick)
        Spacer(modifier = Modifier.height(space24))

        TextWithSingleLink(sentence = stringResource(com.test.chatapp.core.ui.R.string.already_member_login),
            onLinkClicked = {})
    }
}

@Preview
@Composable
fun RegistrationPreview() {
    ChatAppTheme {
        RegistrationScreen(
            uiState = RegistrationState(
                nickName = "",
                name = "",
                phone = ""
            ),
            onNameChange = {},
            onNicknameChange = {},
            onRegisterClick = {})
    }
}