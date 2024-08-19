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
import com.test.authorization.presentation.viewmodel.SignInUiState
import com.test.ui.design.theme.ChatAppTheme
import com.test.ui.design.theme.itemHeight75
import com.test.ui.design.theme.itemWidth104
import com.test.ui.design.theme.space16
import com.test.ui.design.theme.space24
import com.test.ui.design.theme.space32
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.DefaultButton
import com.test.ui.widgets.FormField
import com.test.ui.widgets.LogoTitle
import com.test.ui.widgets.OtpCells
import com.test.ui.widgets.StatusBarInsetsSpacer
import com.test.ui.widgets.SweetBite
import com.test.ui.widgets.TextWithSingleLink

@Composable
fun CheckCodeScreen(
    uiState: SignInUiState,
    onCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit,
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
            text = stringResource(com.test.chatapp.core.ui.R.string.verification),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))

        OtpCells()

        Spacer(modifier = Modifier.height(space24))
    }
}


@Preview(showBackground = true)
@androidx.compose.runtime.Composable
fun CheckCodeScreenPreview() {
    ChatAppTheme {
        CheckCodeScreen(uiState = SignInUiState(
            code = "+7",
            phone = ""), onCodeChange = {}){}
    }
}