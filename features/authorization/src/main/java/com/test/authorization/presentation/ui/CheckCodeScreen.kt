package com.test.authorization.presentation.ui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.test.authorization.presentation.viewmodel.CheckCodeUiState
import com.test.ui.design.theme.ChatAppTheme
import com.test.ui.design.theme.itemHeight75
import com.test.ui.design.theme.itemWidth104
import com.test.ui.design.theme.space16
import com.test.ui.design.theme.space24
import com.test.ui.design.theme.space32
import com.test.ui.widgets.BackgroundColumn
import com.test.ui.widgets.DefaultButton
import com.test.ui.widgets.LogoTitle
import com.test.ui.widgets.OtpCells
import com.test.ui.widgets.StatusBarInsetsSpacer
import com.test.ui.widgets.IconHeader

@Composable
fun CheckCodeScreen(
    uiState: CheckCodeUiState,
    onCodeChange: (String) -> Unit,
    onVerifyClick: () -> Unit
) {
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
            text = stringResource(com.test.chatapp.core.ui.R.string.verification),
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.background
        )
        Spacer(modifier = Modifier.height(space32))


        val textFieldValueState = remember {
            mutableStateOf(
                TextFieldValue(
                    text = uiState.code,
                    selection = TextRange(0)
                )
            )
        }

        OtpCells(
            otpText = textFieldValueState.value,
            onValueChange = {
                onCodeChange(textFieldValueState.value.text)
                textFieldValueState.value = it
            },
            onValueField = {
                onCodeChange(textFieldValueState.value.text)
            }

        )

        Spacer(modifier = Modifier.height(space24))

        DefaultButton(
            modifier = Modifier.fillMaxWidth(),
            textResId = com.test.chatapp.core.ui.R.string.btn_verify, onClick = onVerifyClick)
    }
}


@Preview(showBackground = true)
@Composable
fun CheckCodeScreenPreview() {
    ChatAppTheme {
        CheckCodeScreen(uiState = CheckCodeUiState(
            code = "111111"
            ), onCodeChange = {}){}
    }
}