package com.test.ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.test.ui.design.theme.*

@Composable
fun CartItem(
    modifier: Modifier = Modifier,
    id: String,
    title: String,
    count: String,
   // imageUrl: String,
    onDelete: (String) -> Unit,
    onClick: () -> Unit
) {
    Row(modifier = modifier.clickable(onClick = onClick)) {
        Box(
            modifier = Modifier
                .padding(end = padding16)
                .width(itemSize136)
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(size = cornerRadius8)
                ),
        ) {
            AsyncImage(
                modifier =
                Modifier
                    .align(Alignment.Center),
                model = "https://omnidesk.ru/tilda/548899/images/tild3130-6538-4663-b836-383336336165__group_chats2x.png",
                contentDescription = title
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(space8)
        ) {
            Text(
                text = title, style = bodyLargeBold,
                color = Color.White
            )
            Text(
                text = stringResource(com.test.chatapp.core.ui.R.string.usd, count),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        }

        Image(
            modifier = Modifier.clickable(
                onClick = { onDelete(id) },
            ),
            painter = painterResource(com.test.chatapp.core.ui.R.drawable.ic_cancel),
            contentDescription = "Remove $title from the cart"
        )

    }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
    ChatAppTheme {
        CartItem(
            modifier = Modifier.fillMaxWidth(),
            id = "1",
            title = "perpetua perpetua perpetua perpetua perpetua perpetua" +
                    "perpetua perpetua perpetua ",
            count = "78",
            onClick = {},
            onDelete = {}
        )
    }
}