package com.test.ui.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.test.chatapp.core.ui.R
import com.test.ui.design.theme.*

@Composable
fun TextWithSingleLink(
    modifier: Modifier = Modifier,
    sentence: String,
    entryMarker: String = "{{",
    endMarker: String = "}}",
    defaultSpanStyle: SpanStyle = SpanStyle(
        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
        fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
        fontWeight = MaterialTheme.typography.bodyLarge.fontWeight,
        color = MaterialTheme.colorScheme.background,
    ),
    linkEntrySpanStyle: SpanStyle = SpanStyle(
        fontSize = bodyLargeBold.fontSize,
        fontFamily = bodyLargeBold.fontFamily,
        fontWeight = bodyLargeBold.fontWeight,
        color = MaterialTheme.colorScheme.background,
    ),
    onLinkClicked: () -> Unit = {}
) {

    val source = sentence
    val link = remember { source.substringAfter(entryMarker).substringBefore(endMarker) }
    val entryStart = remember { source.indexOf(entryMarker) }
    val entryEnd = remember { source.indexOf(endMarker) + endMarker.length }
    val sentenceStart = remember { source.take(entryStart) }
    val sentenceEnd = remember { source.takeLast(source.length - entryEnd) }

    val annotatedString = buildAnnotatedString {
        withStyle(defaultSpanStyle) { append(sentenceStart) }
        withStyle(linkEntrySpanStyle) {
            pushStringAnnotation(tag = link, annotation = link)
            append(link)
        }
        withStyle(defaultSpanStyle) { append(sentenceEnd) }
    }

    Box(modifier = modifier) {
        ClickableText(text = annotatedString, onClick = { offset ->
            annotatedString.getStringAnnotations(offset, offset).firstOrNull()?.let { span ->
                onLinkClicked()
            }
        })
    }
}

@Preview()
@Composable
fun TextWithLinkPreview() {
    ChatAppTheme {
        TextWithSingleLink(sentence = stringResource(id = R.string.not_a_member_register),
            onLinkClicked = {})
    }
}