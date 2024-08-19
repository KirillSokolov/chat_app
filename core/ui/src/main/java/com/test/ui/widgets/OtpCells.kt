package com.test.ui.widgets

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.samirahmed.otptextfield.ui.OtpCellProperties
import com.test.ui.design.theme.corner8
import com.test.ui.design.theme.cursor
import com.test.ui.design.theme.itemHeight48
import com.test.ui.design.theme.oneValue
import com.test.ui.design.theme.padding10

@Composable
fun OtpCells() {
    OtpCellProperties(
        otpLength = 6,
        otpCellSize = itemHeight48,
        otpDistanceBetweenCells = padding10,
        otpTextStyle = TextStyle(),
        borderWidth = oneValue,
        borderRound = corner8,
        cursorWidth = cursor,
        cursorColor = Color.Black,
        isHasError = false,
        isHasCursor = true,
        hint = " "

    )
}