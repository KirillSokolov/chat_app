package com.test.ui.widgets

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.corner8
import com.test.ui.design.theme.itemHeight24
import com.test.ui.design.theme.itemHeight56
import com.test.ui.design.theme.itemWidth24


private const val phoneSize = 10

fun inputPhoneType(textField: TextFieldValue): TextFieldValue {
    val selection = textField.selection

    val phone = textField.text.filter{it.isDigit()}
    return if (phone.length > phoneSize) {
        textField.copy(text = phone.dropLast(phone.length - phoneSize), selection = selection)
    }else{
        textField.copy(text = phone, selection = selection)
    }
}


@Composable
fun PhoneField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    @StringRes placeholderResId: Int,
    @DrawableRes leadingIconResId: Int,
    hasError: Boolean = false,
    mask: String = "(000) 000 00 00",
    maskNumber: Char = '0'
) {
    TextField(
        value = value,
        shape = RoundedCornerShape(corner8),
        modifier =
        modifier
            .height(itemHeight56),
        placeholder = {
            Text(
                text = stringResource(id = placeholderResId),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },

        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
        onValueChange =  onValueChange,
        leadingIcon = {
            Image(
                modifier = androidx.compose.ui.Modifier
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
                    modifier = androidx.compose.ui.Modifier
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
        ),
        visualTransformation = PhoneVisualTransformation(mask, maskNumber),
    )
}


class PhoneVisualTransformation(val mask: String, val maskNumber: Char) : VisualTransformation {

    private val maxLength = mask.count { it == maskNumber }

    override fun filter(text: AnnotatedString): TransformedText {

        val trimmed = if (maxLength >= text.length) text else text.dropLast(1)

        val annotatedString = buildAnnotatedString {9
            if (trimmed.isEmpty()) return@buildAnnotatedString

            var maskIndex = 0
            var textIndex = 0
            while (textIndex < trimmed.length && maskIndex < mask.length) {
                if (mask[maskIndex] != maskNumber) {
                    val nextDigitIndex = mask.indexOf(maskNumber, maskIndex)
                    append(mask.substring(maskIndex, nextDigitIndex))
                    maskIndex = nextDigitIndex
                }
                append(trimmed[textIndex++])
                maskIndex++
            }
        }

        return TransformedText(annotatedString, PhoneOffsetMapper(mask, maskNumber))
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PhoneVisualTransformation) return false
        if (mask != other.mask) return false
        if (maskNumber != other.maskNumber) return false
        return true
    }

    override fun hashCode(): Int {
        return mask.hashCode()
    }
}

private class PhoneOffsetMapper(val mask: String, val numberChar: Char) : OffsetMapping {

    override fun originalToTransformed(offset: Int): Int {
        var noneDigitCount = 0
        var i = 0
        while (i < offset + noneDigitCount) {
            try{
                if (mask[i++] != numberChar) noneDigitCount++
            }catch (e:Exception){

            }
        }
        return offset + noneDigitCount
    }

    override fun transformedToOriginal(offset: Int): Int {
        return if (mask.lastIndex >= offset)
            offset - mask.take(offset).count {
                it != numberChar
            }
        else
            0
    }

}