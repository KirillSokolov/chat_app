package com.test.ui.widgets

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.samirahmed.otptextfield.ui.OtpCellProperties
import com.samirahmed.otptextfield.ui.OtpCursor
import com.samirahmed.otptextfield.ui.OtpStatus
import com.samirahmed.otptextfield.ui.TestingTags
import com.samirahmed.otptextfield.ui.TestingTags.CELL_ROW
import com.samirahmed.otptextfield.ui.TestingTags.OTP_TEXT_FIELD
import com.samirahmed.otptextfield.ui.TestingTags.OTP_TEXT_FIELD_Decoration
import com.samirahmed.otptextfield.ui.prepareCellWidthIfNoSpace
import com.test.ui.design.theme.ChatAppTheme
import com.test.ui.design.theme.Error_Color
import com.test.ui.design.theme.Focus_Border
import com.test.ui.design.theme.Focus_Text
import com.test.ui.design.theme.corner8
import com.test.ui.design.theme.cursor
import com.test.ui.design.theme.itemHeight40
import com.test.ui.design.theme.itemHeight48
import com.test.ui.design.theme.oneValue
import com.test.ui.design.theme.padding10
import com.test.ui.design.theme.padding6
import com.test.ui.design.theme.unFocus_Border

@Composable
fun OtpCells(
    otpText:TextFieldValue,
    modifier: Modifier = Modifier,
    onValueChange: (TextFieldValue) -> Unit,
    onValueField: () -> Unit,) {

    OtpTextField(
        modifier = modifier.fillMaxWidth(),
        otpText = otpText,
        otpCellProperties = getProperties(),
        onValueChange = { otpStatus ->
            when(otpStatus){
                is OtpStatus.Typing -> onValueChange(otpStatus.otp) // update state
                is OtpStatus.Filled -> onValueField()
            }
        }
    )
}

@Composable
fun getProperties(): OtpCellProperties{
   return OtpCellProperties(
        otpLength = 6,
        otpCellSize = itemHeight40,
        otpDistanceBetweenCells = padding6,
        otpTextStyle = MaterialTheme.typography.bodyLarge,
        borderWidth = oneValue,
        borderRound = corner8,
        cursorWidth = cursor,
        cursorColor = Color.White,
        isHasError = false,
        isHasCursor = true,
        hint = " "
    )
}


@Composable
fun OtpTextField(
    modifier: Modifier = Modifier,
    otpText: TextFieldValue = TextFieldValue(),
    otpCellProperties: OtpCellProperties = OtpCellProperties(),
    onValueChange: (OtpStatus) -> Unit
) {
    val screenWidth = LocalConfiguration.current.screenWidthDp
    val cellProperties = remember(otpCellProperties) {
        prepareCellWidthIfNoSpace(
            screenWidth = screenWidth,
            cellProperties = otpCellProperties
        )
    }

    CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Ltr) {
        BasicTextField(
            modifier = Modifier.testTag(OTP_TEXT_FIELD),
            value = otpText,
            onValueChange = {
                if (it.text.isDigitsOnly() && it.text.length <= cellProperties.otpLength) {
                    onValueChange(OtpStatus.Typing(it))
                    if (it.text.length == cellProperties.otpLength) {
                        onValueChange(OtpStatus.Filled(it))
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.NumberPassword
            ),
            cursorBrush = SolidColor(Color.Transparent),
            decorationBox = {
                OtpDecorationBox(
                    modifier = modifier.testTag(OTP_TEXT_FIELD_Decoration),
                    cellProperties = cellProperties,
                    otpText = otpText
                )
            }
        )
    }

}

@Composable
fun OtpDecorationBox(
    modifier: Modifier = Modifier,
    cellProperties: OtpCellProperties,
    otpText: TextFieldValue,
) {
    Box(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center).testTag(CELL_ROW),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(cellProperties.otpDistanceBetweenCells)
        ) {
            repeat(cellProperties.otpLength) { index ->
                OtpCell(
                    cellProperties = cellProperties,
                    index = index,
                    text = otpText.text,

                )
            }
        }
    }
}

@Composable
fun OtpCell(
    cellProperties: OtpCellProperties,
    index: Int,
    text: String,
) {
    val isFocusedOtpCell = index <= text.length
    val cursorPosition = index == text.length
    val focusBorderColor =
        remember(cellProperties.isHasError) { if (cellProperties.isHasError) Error_Color else Focus_Border }
    val unFocusBorderColor =
        remember(cellProperties.isHasError) { if (cellProperties.isHasError) Error_Color else unFocus_Border }
    val textColor =
        remember(cellProperties.isHasError) { if (cellProperties.isHasError) Error_Color else Focus_Text }
    val char = when {
        index == text.length -> ""
        index > text.length -> cellProperties.hint
        else -> cellProperties.mask.ifBlank { text[index].toString() }
    }
    Box(
        modifier = Modifier
            .size(cellProperties.otpCellSize)
            .testTag(TestingTags.CELL)
            .border(
                width = cellProperties.borderWidth,
                color = when {
                    isFocusedOtpCell -> focusBorderColor
                    else -> unFocusBorderColor
                },
                shape = RoundedCornerShape(cellProperties.borderRound)
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            modifier = Modifier.testTag(TestingTags.CELL_TEXT),
            text = char,
            style = cellProperties.otpTextStyle,
            color = Color.White,
            textAlign = TextAlign.Center
        )
        if (cursorPosition && cellProperties.isHasCursor) {
            OtpCursor(
                cellProperties = cellProperties
            )
        }
    }
}


@Preview(
    device = "id:pixel_xl",
    showBackground = true, backgroundColor = 0xFFFFFFFF, showSystemUi = true
)
@Composable
fun OtpTextFieldPreview() {
    Column {
        OtpTextField(
            onValueChange = {}
        )
    }
}
